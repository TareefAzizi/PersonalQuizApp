package com.tarren.personalquizapp.ui.tabContainer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.tarren.personalquizapp.R
import com.tarren.personalquizapp.data.repo.UserRepo
import com.tarren.personalquizapp.databinding.FragmentTabContainerBinding
import com.tarren.personalquizapp.ui.Student.QuizParticipation.QuizParticipationFragment
import com.tarren.personalquizapp.ui.Student.UpcomingQuiz.UpcomingQuizFragment
import com.tarren.personalquizapp.ui.adapter.FragmentAdapter
import com.tarren.personalquizapp.ui.home.HomeFragment
import com.tarren.personalquizapp.ui.profile.ProfileFragment
import com.tarren.personalquizapp.ui.Student.studentDashboard.StudentDashboardFragment
import com.tarren.personalquizapp.ui.Teacher.CSVImport.CsvImportFragment
import com.tarren.personalquizapp.ui.Teacher.GroupManagement.GroupManagementFragment
import com.tarren.personalquizapp.ui.Teacher.QuizManagement.QuizManagementFragment
import com.tarren.personalquizapp.ui.Teacher.QuizTiming.QuizTimingFragment
import com.tarren.personalquizapp.ui.Teacher.teacherDashboard.TeacherDashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

// This annotation indicates that Hilt should provide the dependencies for this Fragment.
@AndroidEntryPoint
class TabContainerFragment : Fragment() {
    // Binding object for accessing views in the layout.
    private lateinit var binding: FragmentTabContainerBinding

    // Injecting an instance of UserRepo to handle user-related data operations.
    @Inject
    lateinit var userRepo: UserRepo

    // Called to have the fragment instantiate its user interface view.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflating the layout for this fragment and initializing the binding.
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Called immediately after onCreateView() has returned, but before any saved state has been restored into the view.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetching the current user's ID from Firebase Authentication.
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        // If a user ID is present, fetch user data and set up tabs accordingly.
        if (currentUserId != null) {
            fetchAndSetupUserTabs(currentUserId)
        }
    }

    // Function to fetch user data based on their ID and setup tabs in the ViewPager.
    private fun fetchAndSetupUserTabs(userId: String) {
        // Launching a coroutine within the lifecycle scope of the fragment.
        lifecycleScope.launch {
            // Fetching the user data from the repository.
            val user = userRepo.getUser(userId)
            // Deciding which fragments to display based on the user's role.
            val fragments = when (user?.role) {
                "Student" -> listOf(StudentDashboardFragment(), QuizParticipationFragment(), StudentDashboardFragment(), UpcomingQuizFragment(),  ProfileFragment())
                "Teacher" -> listOf(TeacherDashboardFragment(), CsvImportFragment(), GroupManagementFragment(), QuizManagementFragment(), QuizTimingFragment(),ProfileFragment())
                else -> listOf(HomeFragment(), ProfileFragment())
            }
            // Setting up ViewPager with the determined fragments.
            setupViewPagerWithTabs(fragments)
        }
    }

    // Function to set up the ViewPager2 and TabLayout with the given fragments.
    private fun setupViewPagerWithTabs(fragments: List<Fragment>) {
        // Creating and setting the adapter for ViewPager2 with the list of fragments.
        val fragmentAdapter = FragmentAdapter(this, fragments)
        binding.vpContainer.adapter = fragmentAdapter

        // Setting up TabLayoutMediator to synchronize TabLayout with ViewPager2.
        // Setting up TabLayoutMediator to synchronize TabLayout with ViewPager2.
        TabLayoutMediator(binding.tlTabs, binding.vpContainer) { tab, position ->
            // Dynamically setting the title of tabs based on their position and user role.
            tab.text = getTabTitle(position, fragments)
        }.attach()
    }

    // Function to determine tab titles based on the position and the type of fragments
    private fun getTabTitle(position: Int, fragments: List<Fragment>): String {
        return when {
            fragments[position] is StudentDashboardFragment -> "Student Dashboard"
            fragments[position] is QuizParticipationFragment -> "Participate in Quiz"
            fragments[position] is UpcomingQuizFragment -> "Upcoming Quizzes"
            fragments[position] is TeacherDashboardFragment -> "Teacher Dashboard"
            fragments[position] is CsvImportFragment -> "CSV Import"
            fragments[position] is GroupManagementFragment -> "Group Management"
            fragments[position] is QuizManagementFragment -> "Quiz Management"
            fragments[position] is QuizTimingFragment -> "Quiz Timing"
            fragments[position] is ProfileFragment -> "Profile"
            else -> "Tab $position" // Fallback title for any unidentified fragments
        }
    }
}
