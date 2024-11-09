package com.ytrewq.rosLearning.Services;

import com.ytrewq.rosLearning.Entities.Course;
import com.ytrewq.rosLearning.Entities.User;
import com.ytrewq.rosLearning.Repositories.CourseRepository;
import com.ytrewq.rosLearning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CourseRepository courseRepository;

    public UserService(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Course> getUserCourses(User currentUser) {
        String[] coursesIdsStr = userRepository.findCoursesIdsStrById(currentUser.getId()).split("/;/");

        List<Integer> coursesIds = new ArrayList<>();
        for (String s : coursesIdsStr) {
            if (!s.isEmpty()) {
                coursesIds.add(Integer.parseInt(s));
            }
        }
        return (List<Course>) courseRepository.findAllById(coursesIds);
    }

    public void setUserCourses(User currentUser, List<Course> courses) {
        List<String> coursesIdsStr = new ArrayList<>();
        for (Course course : courses) {
            coursesIdsStr.add(String.valueOf(course.getId()));
        }
        currentUser.setCoursesIdsStr(String.join("/;/", coursesIdsStr));
        userRepository.save(currentUser);
    }

    public Course getUserCourse(User currentUser, Integer courseId) {
        String courseIdStr = courseId.toString();
        if (("/;/" + userRepository.findCoursesIdsStrById(currentUser.getId()) + "/;/").contains("/;/" + courseIdStr + "/;/")) {
            Optional<Course> course = courseRepository.findById(courseId);
            return course.orElse(null);
        }
        return null;
    }


    public void addUserCourse(User currentUser, Course course) {
        String courseId = String.valueOf(course.getId());
        String coursesIdsStr = userRepository.findCoursesIdsStrById(currentUser.getId());
        if (coursesIdsStr == null) {
            currentUser.setCoursesIdsStr("");
            coursesIdsStr = "";
        }
        if (!coursesIdsStr.isEmpty()) {
            currentUser.setCoursesIdsStr(coursesIdsStr + "/;/" + courseId);
        } else {
            currentUser.setCoursesIdsStr(courseId);
        }
        userRepository.save(currentUser);
    }

    public void removeUserCourse(User currentUser, Integer courseId) {
        String courseIdStr = courseId.toString();
        String coursesIdsStr =  userRepository.findCoursesIdsStrById(currentUser.getId());
        coursesIdsStr = "/;/" + coursesIdsStr + "/;/";
        coursesIdsStr = coursesIdsStr.replace("/;/" + courseIdStr + "/;/", "/;/");
        if (!coursesIdsStr.equals("/;/")) {
            coursesIdsStr = coursesIdsStr.substring(3, coursesIdsStr.length() - 3);
        } else {
            coursesIdsStr = "";
        }
        currentUser.setCoursesIdsStr(coursesIdsStr);
        userRepository.save(currentUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
