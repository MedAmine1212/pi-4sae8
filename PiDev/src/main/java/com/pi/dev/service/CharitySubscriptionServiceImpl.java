package com.pi.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.dev.models.CharitySubscription;
import com.pi.dev.models.CivilStatus;
import com.pi.dev.models.EducationLevel;
import com.pi.dev.models.ScientificCertificate;
import com.pi.dev.models.health;
import com.pi.dev.repository.CharitySubscriptionRepository;
import com.pi.dev.serviceInterface.ICharitySubscriptionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CharitySubscriptionServiceImpl implements ICharitySubscriptionService {
	
	@Autowired
	CharitySubscriptionRepository CSubRepo ;
	
	
	@Override
	public void addCS(CharitySubscription Cs) {
		// TODO Auto-generated method stub
		CSubRepo.save(Cs);
		
	}

	@Override
	public CharitySubscription updateCS(CharitySubscription Cs, Long idCharity) {
		// TODO Auto-generated method stub
		Cs.setIdCharity(idCharity);
		return CSubRepo.save(Cs);
	}

	@Override
	public boolean deleteCS(Long idCharity) {
		// TODO Auto-generated method stub
		try {
			CSubRepo.deleteById(idCharity);
			return true;
		}catch (Exception e) {
			return  false;
		}
		
	}

	@Override
	public List<CharitySubscription> AllCS() {
		// TODO Auto-generated method stub
		return CSubRepo.findAll();
	}

	@Override
	public Optional<CharitySubscription> findCSbyid(Long idCharity) {
		// TODO Auto-generated method stub
		return CSubRepo.findById(idCharity);
	}

	@Override
	public void calculScore(Long idCharity) {
		// TODO Auto-generated method stub
		CharitySubscription c = CSubRepo.getById(idCharity);
		
		int s = 0 ; 
		
		if (c.getNbrChildrenMinor() > 0){
			 s = s + c.getNbrChildrenMinor() ;
		 }
		if (c.getNbrChildrenHandicap() > 0 ){
			s = s + c.getNbrChildrenHandicap() ;
		}
		
		if (c.getHealth().equals(health.good_health)){
			s = s + 1 ;
		}else { 
			s = s + 2;
		}
		
		if(c.getCivilStatus().equals(CivilStatus.Widow)){
			s = s + 4;
		}else if (c.getCivilStatus().equals(CivilStatus.Divorced)){
			s = s + 3 ; 
		} else if (c.getCivilStatus().equals(CivilStatus.Single)){
			s = s + 2 ;
		}else if (c.getCivilStatus().equals(CivilStatus.Married)){
			s = s + 1 ;
		}
		
		if (c.getEducationLevel().equals(EducationLevel.Educated)){
			s= s + 2 ;
		}else if (c.getEducationLevel().equals(EducationLevel.Uneducated)){
			s = s + 1 ;
		}
		
		if (c.getScientificCertificate().equals(ScientificCertificate.HighEducation)){
			s = s + 3 ;
		}else if (c.getScientificCertificate().equals(ScientificCertificate.Secondary)){
			s = s + 2;
		}else if (c.getScientificCertificate().equals(ScientificCertificate.Preparatory)){
			s = s + 1;
		}else if (c.getScientificCertificate().equals(ScientificCertificate.Primary))
			s = s + 1 ;
		
		c.setScore(s);
		CSubRepo.save(c);
	}

	@Override
	public void CompareScore() {
		// TODO Auto-generated method stub
		List<CharitySubscription> compareScore = CSubRepo.CompreScore() ;
		for (CharitySubscription charitySubscription : compareScore) {
			System.out.println(charitySubscription);
		}
		int nbChildrenMinMax=compareScore.get(0).getNbrChildrenMinor();
		int pos=0;
		for(int i=1;i<compareScore.size();i++){
			   if(compareScore.get(i).getNbrChildrenMinor()>nbChildrenMinMax){
			   	pos=i;
			    }
			   CharitySubscription cs = compareScore.get(pos);
			   System.out.println("elli aando akther sghar MINOR houwa :  " + cs);
		}
			   
		int nbrChildrenHandicapMax = compareScore.get(0).getNbrChildrenHandicap();
		int p = 0 ;
		for(int i=1;i<compareScore.size();i++){
			if (compareScore.get(i).getNbrChildrenHandicap()>nbrChildrenHandicapMax){
				p = i ;
			}
			   CharitySubscription cS = compareScore.get(p);
			   System.out.println("elli aando akther sghar HANDICAP houwa :  " + cS);
		}
		
		

		
		/*List<CharitySubscription> compareScore = CSubRepo.CompreScore() ;
		int nbChildrenMinMax=compareScore.get(0).getNbrChildrenMinor();
		int pos=0;
		for(int i=1;i<compareScore.size();i++){
		   if(compareScore.get(i).getNbrChildrenMinor()>nbChildrenMinMax){
		   	pos=i;
		    }
		}
		CharitySubscription cs = compareScore.get(pos);
		//System.out.println("bibhbbibi^bîbîbîbîbi^bi^b"+cs.getName());
		return cs;*/
		
		//List<CharitySubscription> compareAtt = new ArrayList() ;
		//List<CharitySubscription> allCS = CSubRepo.findAll();
		//Long idCharity;
		//int chS = CSubRepo.getById(idCharity).getNbrChildrenMinor();
		//for (CharitySubscription cs : allCS) {
			//if (cs.getNbrChildrenMinor() ){
				
			//}
		//}
		
		//bech t9aren elli aand'hom akwa score w tchouf kol wahed kifeh jeeeb core mteeou 
		// w baaed taaml bel tartiib ken aand'hom nafes score f haja tetaada leeli baadou yaani hnee bech todkhel feha hkeyt priorité 
		// w haja okhraa mouhema .. lazmek tbadel score fel lekher 
	}

	

}
