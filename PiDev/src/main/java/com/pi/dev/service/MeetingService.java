package com.pi.dev.service;

import com.pi.dev.models.Contributor;
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
    public void setContributor(Long idMeeting , Long idContributor) {
        Meeting m = mr.getById(idMeeting);
        if(m.getConsultedBy() == null){
            m.setConsultedBy(cr.findById(idContributor).orElse(null));
            m.setState(State.Accepted);
            mr.save(m);
        }
    }
    @Override
     public List<Meeting> listMeetingsByDisponibilty(Long idContributor){
        List<Meeting>lm = mr.findAll();
        // List<Meeting>ListMeetings = new ArrayList<>();
        
        // for (Meeting m : lm){
        //     if(!(m.getConsultedBy().equals(cr.findById(idContributor).orElse(null)))||(m.getConsultedBy().equals(null))){
        //         ListMeetings.add(m);
        //     }
        // }

        Contributor cont = cr.findById(idContributor).get();
        
        List <Meeting> ListFiltredMeetings = new ArrayList<>();
        List <Meeting> listMettings = mr.findAllByConsultedBy(cont);
        System.out.println("this is what i need:"+listMettings);
        for (Meeting m:lm){
            if ((m.getDate().after(cont.getDisponibiltyStart()) 
            && (m.getDate().before(cont.getDisponibiltyOver())))){
                
                for(Meeting mt: listMettings) {
                    if(m.getDate().compareTo(mt.getDate()) != 0){
                        ListFiltredMeetings.add(m);
                    }
                }
            }
        }
        return ListFiltredMeetings;

     }
}
