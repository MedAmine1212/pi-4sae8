package com.pi.dev.service;

import com.pi.dev.models.Meeting;
import com.pi.dev.models.State;
import com.pi.dev.repository.ContributorRepository;
import com.pi.dev.repository.MeetingRepository;
import com.pi.dev.serviceInterface.IMeetingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MeetingService implements IMeetingService {
    @Autowired
    MeetingRepository mr;
    @Autowired
    ContributorRepository cr;

    @Override
    public Meeting addMeeting(Meeting meeting) {
        return mr.save(meeting);
    }
    @Override
    public Meeting updateMeeting(Meeting meeting, Long id) {
         
        meeting.setId(id);
        return mr.save(meeting);
    }
    @Override
    public List<Meeting> listMeetings() {
        
        return mr.findAll();
    }
    @Override
    public void setContributor(Long idMeeting, Long idContributor) {
        Meeting m = mr.findById(idMeeting).orElse(null);
        if(m.getConsultedBy().equals(null)){
            m.setConsultedBy(cr.findById(idContributor).orElse(null));
            m.setState(State.Accepted);
            mr.save(m);
        }
    }
     public List<Meeting> listMeetingsByDisponibilty(Long idContributor){
        List<Meeting>lm = mr.findAll();
        // List<Meeting>ListMeetings = new ArrayList<>();
        
        // for (Meeting m : lm){
        //     if(!(m.getConsultedBy().equals(cr.findById(idContributor).orElse(null)))||(m.getConsultedBy().equals(null))){
        //         ListMeetings.add(m);
        //     }
        // }

        
        List<Meeting> ListFiltredMeetings = new ArrayList<>();
        System.out.println(cr.getById(idContributor));
        for (Meeting m:lm){
            if ((m.getDate().after(cr.getById(idContributor).getDisponibiltyStart()) 
            && (m.getDate().before(cr.getById(idContributor).getDisponibiltyOver())))){
                ListFiltredMeetings.add(m);
            }
        }
        return ListFiltredMeetings;

     }
}
