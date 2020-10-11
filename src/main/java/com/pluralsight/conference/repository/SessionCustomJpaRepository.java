package com.pluralsight.conference.repository;

import com.pluralsight.conference.models.Session;

import java.util.List;

public interface SessionCustomJpaRepository {
    List<Session> customGetSessions();
}
