package com.pi.dev.service;

import com.pi.dev.models.*;
import com.pi.dev.repository.*;
import com.pi.dev.serviceInterface.IAdvertisementService;
import com.pi.dev.serviceInterface.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
@Slf4j
public class AdvertisementServiceImpl implements IAdvertisementService {

	@Autowired
	AdvertisementRepository advertisementRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired

	PostServiceImpl postService;

	@Override
	public Advertisement addAdvertisement(Advertisement ad) {
		return advertisementRepository.save(ad);
	}

	@Override
	public Boolean deleteAd(Long adId) {
		try {
			advertisementRepository.deleteById(adId);
			return  true;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return  false;
		}
	}

	@Override
	public List<Advertisement> findAdsForUser(Long userId) {
		User user = userRepository.findById(userId).get();
		List<String> words = postService.getWordByUser(user);
		List<Advertisement> ads = advertisementRepository.findAllActiveAds();
		List<Advertisement> finalAds = new ArrayList<Advertisement>();
		for(Advertisement ad: ads) {
			boolean keepIt = false;
			for(String word: words) {
				if (ad.getName().contains(word)) {
					keepIt = true;
					break;
				}
			}
			if(keepIt) {
				finalAds.add(ad);
			}
		}
		if(finalAds.isEmpty()){

			finalAds = advertisementRepository.findAll();
		}
		finalAds.sort(Comparator.comparing(Advertisement::getCoast));
		Collections.reverse(finalAds);

		return finalAds;
	}

	@Scheduled(cron = "@daily")
//	@Scheduled(fixedRate = 30000)
	@Override
	public void deleteAds() {
		List<Advertisement> ads = advertisementRepository.findAll();
		LocalDate date = LocalDate.now();
		for (Advertisement ad: ads) {
			LocalDate dt = ad.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if(date.compareTo(dt) > 0 || ad.getWantedViewsNbr() == ad.getWantedViewsNbr()) {
				ad.setActive(false);
				advertisementRepository.save(ad);
			}
		}
	}
}