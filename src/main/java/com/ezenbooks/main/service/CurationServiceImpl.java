package com.ezenbooks.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.ezenbooks.main.dao.CurationDAO;
import com.ezenbooks.main.dto.BookDTO;
import com.ezenbooks.main.dto.ReviewDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationServiceImpl extends Calculator implements CurationService {
	
	private final CurationDAO dao;

	@Override
	public List<ReviewDTO> dataSetProcess() {
		return dao.getReviewData();
	}
	
	@Override
	public List<BookDTO> curationProcess(int user_id, int bought) {
		int NUM_USERS = dao.getUsersCount();
		int NUM_BOOKS = dao.getBooksCount();
		
		List<ReviewDTO> reviewDataList = dao.getReviewData();
		
		/*
		 * 유틸리티 행렬을 얻는다.
		 */
		double[][] u = new double[NUM_USERS + 1][NUM_BOOKS + 1];
		
		for (ReviewDTO reviewDTO : reviewDataList) {
			int i = reviewDTO.getUser_id(); // 사용자
			int j = reviewDTO.getBook_num(); // 아이템
			u[i][j] = (double)reviewDTO.getReview_rating();
		}
		
		/*
		 * 유사도 행렬을 얻는다.
		 */
		double[][] s = new double[NUM_BOOKS + 1][NUM_BOOKS + 1];
		
		for (int j = 1; j <= NUM_BOOKS; j++) {
			for (int k = 1; k <= NUM_BOOKS; k++) {
				s[j][k] = cosine(u, j, k, NUM_USERS);
			}
		}
		
		// 유사도 정렬
		Item itemBought = new Item(bought, s, u, NUM_USERS);
		
		Set<Item> set1 = new TreeSet<>(itemBought.new SimilarityComparator());
		for (int j = 1; j <= NUM_BOOKS; j++) {
			if (u[user_id][j] == 0) {
				set1.add(new Item(j));
			}
		}
		
		// 인기도 정렬
		Set<Item> set2 = new TreeSet<>(itemBought.new PopularityComparator());
		int count1 = 0;
		for (Item item : set1) {
			set2.add(item);
			if(++count1 == 8) {
				break;
			}
		}
		
		List<Integer> list = new ArrayList<>();
		// 출력
		int count2 = 0;
		for (Item item : set2) {
//			System.out.printf("  %d", item.index);
			list.add(item.index);
			if (++count2 == 7) {
				break;
			}
		}
		System.out.println();
		
		List<BookDTO> result = new ArrayList<>();
		result = dao.getCurationList(list);
		
		return result;
	}
	
} // end class
