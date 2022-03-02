package com.pi.dev.repository;

import com.pi.dev.models.Advertisement;
import com.pi.dev.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
