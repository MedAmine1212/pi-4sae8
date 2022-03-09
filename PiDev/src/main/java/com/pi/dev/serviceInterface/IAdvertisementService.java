package com.pi.dev.serviceInterface;

import com.pi.dev.models.Advertisement;
import com.pi.dev.models.Post;
import com.pi.dev.models.PostLike;
import com.pi.dev.models.Rating;

import java.util.List;

public interface IAdvertisementService {

	Advertisement addAdvertisement(Advertisement ad);
	Boolean deleteAd(Long adId);
	List<Advertisement> findAdsForUser(Long userId);
	void  deleteAds();
}
