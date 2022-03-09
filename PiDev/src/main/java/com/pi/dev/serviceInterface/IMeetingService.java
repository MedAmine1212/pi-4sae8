package com.pi.dev.serviceInterface;

import java.util.List;

import com.pi.dev.models.Meeting;

public interface IMeetingService {
    List<Meeting> listMeetings();
    Meeting addMeeting (Meeting meeting);
    Meeting updateMeeting(Meeting meeting,Long idMeeting);
    void setContributor(Long idMeeting ,Long idContributor);
    List<Meeting> listMeetingsByDisponibilty(Long idContributor);

}
