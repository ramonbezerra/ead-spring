package com.example.ead.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import com.example.ead.repository.CourseRepository
import com.example.ead.model.Course

@RestController
@RequestMapping("/api/courses")
class CourseController(@Autowired private val courseRepository : CourseRepository) {
    
    @GetMapping
    fun getAll() : ResponseEntity<List<Course>>{
        return ResponseEntity.ok().body(courseRepository.findAll())
    }

    @GetMapping("{id}")
    fun getOne(@PathVariable("id") courseId: Int): ResponseEntity<Course> {
        val course = courseRepository.findById(courseId.toLong()).orElse(null)
        return if (course != null) ResponseEntity.ok().body(course)
                else ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createCourse(@RequestBody course : Course): ResponseEntity<Course> {
        val createdCourse = courseRepository.save(course)
        return ResponseEntity(createdCourse, HttpStatus.CREATED)
    }
}
