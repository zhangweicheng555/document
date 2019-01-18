package com.kuandeng.tags.export.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.kuandeng.tags.tag.dao.TagDao;
import com.kuandeng.tags.tag.model.Tag;

/**
 * 
 * @author a-zhangweicheng
 *
 */
@Service
public class ExportService {

	private static final String SUREY_COMPLEMENT_TAG = "tag_survey_complement_tag";
	@Autowired
	private TagDao tagDao;

	@SuppressWarnings("unchecked")
	public List<Tag> surveycomplementtag(String adcode, String state) {
		Query query = new Query();
		if (StringUtils.isNoneBlank(adcode)) {
			query.addCriteria(Criteria.where("properties.ADCODE").in(Arrays.asList(adcode.split(","))));
		}
		if (StringUtils.isNoneBlank(state)) {
			query.addCriteria(Criteria.where("properties.STATE").in(Arrays.asList(state.split(","))));
		}
		List<Tag> tags = tagDao.find(query, SUREY_COMPLEMENT_TAG);
		if (tags != null && tags.size() > 0) {
			for (Tag tag : tags) {
				Object geometryObj = tag.getGeometry();
				LinkedHashMap<String, Object> geometry = (LinkedHashMap<String, Object>) geometryObj;
				if (geometry != null) {
					Object obj = geometry.get("coordinates");
					ArrayList<ArrayList<Double>> datas = (ArrayList<ArrayList<Double>>) obj;
					for (ArrayList<Double> listData : datas) {
						listData.add(0, listData.get(0) + 9.218603);
						listData.add(1, listData.get(1) + 48.683012);
					}
				}
			}
		}
		return tags;
	}

}
