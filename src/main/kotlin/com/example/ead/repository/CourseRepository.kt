package com.example.ead.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.ead.model.Course

interface CourseRepository : JpaRepository<Course, Long>