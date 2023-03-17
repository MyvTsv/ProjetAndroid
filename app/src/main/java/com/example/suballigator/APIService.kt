package com.example.suballigator

import com.example.suballigator.entity.*
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("aptitude")
    suspend fun getAptitude(): List<Aptitude>

    @GET("containSkill")
    suspend fun getContainSkill(): List<ContainSkill>

    @GET("content")
    suspend fun getContent(): List<Content>

    @GET("formation")
    suspend fun getFormation(): List<Formation>

    @GET("initiator")
    suspend fun getInitiator(): List<Initiator>

    @GET("level")
    suspend fun getLevel(): List<Level>

    @GET("participant")
    suspend fun getParticipant(): List<Participant>

    @GET("session")
    suspend fun getSession(): List<Session>

    @GET("skill")
    suspend fun getSkill(): List<Skill>

    @GET("status")
    suspend fun getStatus(): List<Status>

    @GET("student")
    suspend fun getStudent(): List<Student>

    @GET("trainingManager")
    suspend fun getTrainingManager(): List<TrainingManager>

}