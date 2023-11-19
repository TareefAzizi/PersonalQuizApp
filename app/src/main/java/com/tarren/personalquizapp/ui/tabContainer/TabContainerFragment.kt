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
import com.tarren.personalquizapp.ui.adapter.FragmentAdapter
import com.tarren.personalquizapp.ui.home.HomeFragment
import com.tarren.personalquizapp.ui.profile.ProfileFragment
import com.tarren.personalquizapp.ui.Student.studentDashboard.StudentDashboardFragment
import com.tarren.personalquizapp.ui.Teacher.teacherDashboard.TeacherDashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TabContainerFragment : Fragment() {
    private lateinit var binding: FragmentTabContainerBinding

    @Inject
     lateinit var userRepo: UserRepo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            fetchAndSetupUserTabs(currentUserId)
        }
    }

    private fun fetchAndSetupUserTabs(userId: String) {
        lifecycleScope.launch {
            val user = userRepo.getUser(userId)
            val fragments = when (user?.role) {
                "Student" -> listOf(StudentDashboardFragment(), ProfileFragment())
                "Teacher" -> listOf(TeacherDashboardFragment(), ProfileFragment())
                else -> listOf(HomeFragment(), ProfileFragment())
            }
            setupViewPagerWithTabs(fragments)
        }
    }

    private fun setupViewPagerWithTabs(fragments: List<Fragment>) {
        val fragmentAdapter = FragmentAdapter(this, fragments)
        binding.vpContainer.adapter = fragmentAdapter

        TabLayoutMediator(binding.tlTabs, binding.vpContainer) { tab, position ->
            tab.text = when (position) {
                0 -> "Dashboard" // Adjust according to your tab titles
                1 -> "Profile"
                else -> "Tab $position"
            }
        }.attach()
    }
}