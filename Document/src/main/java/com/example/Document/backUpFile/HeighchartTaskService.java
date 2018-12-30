package com.boot.kaizen.backUpFile;
/*package com.boot.security.server.backUpFile;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import com.byb.base.service.BaseService;
import com.byb.core.jsonutil.ApplicationConst;
import com.byb.core.jsonutil.MyUtil;
import com.byb.core.report.export.pojo.HighChartModel;
import com.byb.core.system.organization.pojo.PersonVo;
import com.byb.core.task.heighchart.pojo.HeightChartExportModel;
import com.byb.core.task.heighchart.pojo.HeightchartModel;
import com.byb.core.task.heighchart.pojo.SeriesModel;
import com.byb.util.tools.StringUtil;
*//**
 *heighchart  ͼ������ͳ��
 *//*
public class HeighchartTaskService extends BaseService{

	*//**
	 *  �Ϻ�Ͷ�߹�����ͳ��  ���Ϻ�Ͷ�ߣ�
		ͳ��ͼ��1���գ�����7��Ϊ��ʾ����ͳ��
		ÿ��Ͷ�������ԡ��յ����ڡ��н���ͳ�ƣ�
		ÿ�ջص������ԡ��ص����ڡ��н���ͳ�ƣ�
	 * @throws Exception 
	 *//*
	public HeightchartModel ComplainStatNumByDay(String pid,String type,String begindate,String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("�յ���", "column");
		SeriesModel seriesModelTwo=new SeriesModel("�ص���", "spline");
		
		if (StringUtil.isBlank(type) ) {
			listDates=MyUtil.getBeforeSevenDayByNow();*//**�õ�Ĭ��ǰ��������ڼ���*//*
			highChartModel.setBeginDate(MyUtil.getDateBeforSevenDay(-7));
			highChartModel.setEndDate(MyUtil.getDateBeforSevenDay(-1));
		}else {
			if (("day").equals(type)) {*//**����ͳ��*//*
				listDates=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(begindate, enddate));*//**�õ�ָ�����ڼ���*//*
			}
			*//**����ͳ��*//*
			if (("month").equals(type)) {
				listDates=MyUtil.queryMonthListByDate(begindate, enddate);
			}
		}
		
		if (listDates.size()>0) {
			if (("month").equals(type)) {//����
				for (int i = 0; i < listDates.size(); i++) {
					String dateModel=listDates.get(i);
					seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where acquiringDate like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' and acquiringDate between '"+begindate+"'  and '"+MyUtil.getSomeDateByDay(enddate, 1)+"' ")));
					seriesModelTwo.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where complainBackStepDateLast like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' and complainBackStepDateLast between '"+begindate+"'  and '"+MyUtil.getSomeDateByDay(enddate, 1)+"' ")));
				}
			}else {//����
				for (int i = 0; i < listDates.size(); i++) {
					String dateModel=listDates.get(i);
					seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where acquiringDate like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' ")));
					seriesModelTwo.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where complainBackStepDateLast like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' ")));
				}
			}
			highChartModel.setCategories(listDates);
			highChartModel.getSeries().add(seriesModelOne);
			highChartModel.getSeries().add(seriesModelTwo);
		}
		
       if (session.getAttribute(ApplicationConst.complainStatNumByDay) !=null) {
		session.removeAttribute(ApplicationConst.complainStatNumByDay);
       }
       session.setAttribute(ApplicationConst.complainStatNumByDay, highChartModel);
		
       return highChartModel;
	}
	*//**
	 *  ͳ��ͼ��1���£�������Ϊ��ʾ����ͳ��  ���Ϻ�Ͷ�ߣ�
		����Ͷ�������ԡ��յ����ڡ��н��л���ͳ�ơ�Y/N����Y��������
	 *//*
	public HeightchartModel complainStatNumPromoto(String pid,String type,String begindate,String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("����Ͷ����", "column");
		
		if (StringUtil.isBlank(type) ) {*//**Ĭ��Ϊ��һ���*//*
			listDates=MyUtil.queryMonthListByDate(MyUtil.getCurrentYearStartTime(), MyUtil.getCurrentYearEndTime());
		}else {
			*//**����ͳ��*//*
			listDates=MyUtil.queryMonthListByDate(begindate, enddate);
		}
		
		if (listDates.size()>0) {
			for (int i = 0; i < listDates.size(); i++) {
				String dateModel=listDates.get(i);
				seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where acquiringDate like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' and colplainPromoto='Y' ")));
			}
			highChartModel.setCategories(listDates);
			highChartModel.getSeries().add(seriesModelOne);
		}
		 if (session.getAttribute(ApplicationConst.complainStatNumPromoto) !=null) {
				session.removeAttribute(ApplicationConst.complainStatNumPromoto);
		       }
		session.setAttribute(ApplicationConst.complainStatNumPromoto, highChartModel);
				
		return highChartModel;
	}
	*//**
	 *  ����������ͳ��  ���Ϻ�Ͷ�ߣ�
		ͳ��ͼ��1���գ�����7��Ϊ��ʾ����ͳ��
		ÿ�ղ��Լƻ������ԡ��������ڡ�+�����Ը��ڡ��н���ͳ�ƣ�
		ÿ�ղ�����������ԡ���ɲ������ڡ��н���ͳ�ƣ�
	 *//*
	public HeightchartModel complainStatNumTestDate(String pid,String type,String begindate,String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("#CQT down", "spline");
		SeriesModel seriesModelTwo=new SeriesModel("#CQT planned", "column");
		
		if (StringUtil.isBlank(type) ) {
			listDates=MyUtil.getBeforeSevenDayByNow();*//**�õ�Ĭ��ǰ��������ڼ���*//*
			highChartModel.setBeginDate(MyUtil.getDateBeforSevenDay(-7));
			highChartModel.setEndDate(MyUtil.getDateBeforSevenDay(-1));
		}else {
			if (("day").equals(type)) {*//**����ͳ��*//*
				listDates=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(begindate, enddate));*//**�õ�ָ�����ڼ���*//*
			}
		}
		
		if (listDates.size()>0) {
			for (int i = 0; i < listDates.size(); i++) {
				String dateModel=listDates.get(i);
				seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where complainTestDate like '%"+dateModel+"%' and deleted=0 and projId='"+pid+"' ")));
				*//**�������ڡ�+�����Ը���*//*
				int s=getRowsCount("select id from t_workflow_complain where complainTestBeginDate like '%"+dateModel+"%' and complainTestChangeDate is null and deleted=0 and projId='"+pid+"' ");
				int m=getRowsCount("select id from t_workflow_complain where  complainTestChangeDate like '%"+dateModel+"%' and complainTestBeginDate is null and deleted=0 and projId='"+pid+"' ");
				seriesModelTwo.getData().add(Long.valueOf(s+m));
			}
			highChartModel.setCategories(listDates);
			highChartModel.getSeries().add(seriesModelOne);
			highChartModel.getSeries().add(seriesModelTwo);
		}
		if (session.getAttribute(ApplicationConst.complainStatNumTestDate) !=null) {
			session.removeAttribute(ApplicationConst.complainStatNumTestDate);
	       }
	       session.setAttribute(ApplicationConst.complainStatNumTestDate, highChartModel);
			
		return highChartModel;
	}
	*//**
	 *  ͳ��ͼ��1���£�����7��ּ����ⶨλͳ��  ���Ϻ�Ͷ�ߣ�
	 * @throws Exception 
	 *//*
	public HeightchartModel complainStatNumPosition(String pid, String type,String begindate, String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("�ּ����ⶨλ", "column");
		
		String begindateModel=null;
		String enddateModel=null;
		if (StringUtil.isBlank(type) ) {
			begindateModel=MyUtil.getDateBeforSevenDay(-7);
			enddateModel=MyUtil.getDateBeforSevenDay(-1);
		}else {
			begindateModel=begindate;
			enddateModel=enddate;
		}
		
		*//**���㿪ʼ���� ���������� ֮��Ĳ�ͬ�ķּ��������*//*
		try {
			ps = ct.prepareStatement("select distinct(complainPosition) complainPosition from t_workflow_complain where acquiringDate  between '"+begindateModel+"'  and '"+MyUtil.getSomeDateByDay(enddateModel, 1)+"'  and deleted=0 and projId='"+pid+"'  ");
	        rs = ps.executeQuery();
	        while(rs.next()){
				listDates.add(rs.getString("complainPosition"));
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		
		if (listDates.size()>0) {//
			for (int i = 0; i < listDates.size(); i++) {
				String dateModel=listDates.get(i);
				seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where complainPosition='"+dateModel+"' and  deleted=0 and projId='"+pid+"' and acquiringDate between '"+begindateModel+"'  and '"+MyUtil.getSomeDateByDay(enddateModel, 1)+"' ")));
			}
		}
		highChartModel.setCategories(listDates);
		highChartModel.getSeries().add(seriesModelOne);
		
		if (session.getAttribute(ApplicationConst.complainStatNumPosition) !=null) {
			session.removeAttribute(ApplicationConst.complainStatNumPosition);
	       }
	       session.setAttribute(ApplicationConst.complainStatNumPosition, highChartModel);
			
		
		return highChartModel;
	}
	
	*//**
	 *  ͳ��ͼ��1���£�����7��������ⶨλͳ��  ���Ϻ�Ͷ�ߣ�
	 *//*
	public HeightchartModel complainStatNumTestPosition(String pid, String type,String begindate, String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("�������ⶨλ", "column");
		
		String begindateModel=null;
		String enddateModel=null;
		if (StringUtil.isBlank(type) ) {
			begindateModel=MyUtil.getDateBeforSevenDay(-7);
			enddateModel=MyUtil.getDateBeforSevenDay(-1);
		}else {
			begindateModel=begindate;
			enddateModel=enddate;
		}
		
		*//**���㿪ʼ���� ���������� ֮��Ĳ�ͬ�Ĳ����������*//*
		try {
			ps = ct.prepareStatement("select distinct(complainTestPosition) complainTestPosition from t_workflow_complain where complainTestDate  between '"+begindateModel+"'  and '"+MyUtil.getSomeDateByDay(enddateModel, 1)+"'  and deleted=0 and projId='"+pid+"'  ");
	        rs = ps.executeQuery();
	        while(rs.next()){
				listDates.add(rs.getString("complainTestPosition"));
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		if (listDates.size()>0) {//
			for (int i = 0; i < listDates.size(); i++) {
				String dateModel=listDates.get(i);
				seriesModelOne.getData().add(Long.valueOf(getRowsCount("select id from t_workflow_complain where complainTestPosition='"+dateModel+"' and  deleted=0 and projId='"+pid+"' and complainTestDate between '"+begindateModel+"'  and '"+MyUtil.getSomeDateByDay(enddateModel, 1)+"' ")));
			}
		}
		highChartModel.setCategories(listDates);
		highChartModel.getSeries().add(seriesModelOne);
		if (session.getAttribute(ApplicationConst.complainStatNumTestPosition) !=null) {
			session.removeAttribute(ApplicationConst.complainStatNumTestPosition);
	       }
	       session.setAttribute(ApplicationConst.complainStatNumTestPosition, highChartModel);
			
		return highChartModel;
	}
	
	
	*//**
	 * ��7��ÿ�չ�������ʱ����ƽ�����գ���ͳ��  
	 * *//*
	public HeightchartModel complainStatNumBackStepDateLast(String pid, String type,String begindate, String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("LT to Solve Complaint", "column");
		
		
		if (StringUtil.isBlank(type) ) {
			listDates=MyUtil.getBeforeSevenDayByNow();*//**�õ�Ĭ��ǰ��������ڼ���*//*
		}else {
			if (("day").equals(type)) {
				listDates=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(begindate, enddate));*//**�õ�ָ�����ڼ���*//*
			}
			if (("month").equals(type)) {*//**����*//*
				listDates=MyUtil.queryMonthListByDate(begindate, enddate);*//**�õ�ָ�����ڼ���*//*
			}
		}
		
		if (listDates.size()>0) {//
			for (int i = 0; i < listDates.size(); i++) {
				long l=0;
				int num=0;//ͳ��ÿ��ѭ��������ʹ��
				String dateModel=listDates.get(i);
				*//**�������ʱ��Ļص���¼  ������ص�ʱ����յ�ʱ��*//*
				try {
					ps = ct.prepareStatement("select acquiringDate,complainBackStepDateLast  from t_workflow_complain where complainBackStepDateLast  like '%"+dateModel+"%'  and deleted=0 and projId='"+pid+"'  ");
			        rs = ps.executeQuery();
			        while(rs.next()){
			        	num++;
			        	l +=MyUtil.queryMillSecondByTwoDate(rs.getString("acquiringDate"), rs.getString("complainBackStepDateLast"));
					}
			        if (l !=0) {
			        	if (("month").equals(type)) {
			        		seriesModelOne.getData().add(Double.valueOf(MyUtil.queryDoubleByMillSecond(l/MyUtil.getDaysByYearMonth(dateModel))));
						}else {
							seriesModelOne.getData().add(Double.valueOf(MyUtil.queryDoubleByMillSecond(l/num)));
						}
					}else {
						seriesModelOne.getData().add(0);
					}
				} catch (Exception e) {
				}finally{
					super.close(ps, rs);
				}
			}
		}
		highChartModel.setCategories(listDates);
		highChartModel.getSeries().add(seriesModelOne);
		if (session.getAttribute(ApplicationConst.complainStatNumBackStepDateLast) !=null) {
			session.removeAttribute(ApplicationConst.complainStatNumBackStepDateLast);
	       }
	       session.setAttribute(ApplicationConst.complainStatNumBackStepDateLast, highChartModel);
			
		
		return highChartModel;
	}
	
	*//**
	 * ÿ��ÿ����������ʱ����ƽ����ͳ�� ����
	 * *//*
	public HeightchartModel complainStatNumAvgTimeDay(String pid, String type,String begindate, String enddate,List<String> personIds,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listDates=personIds;//��ǰ��Ŀ����Ա��id����
		List<String> listNames=new ArrayList<String>();
		*//***
		 * �ó���ǰ��¼�˵�����
		 *//*
		for (int i = 0; i < listDates.size(); i++) {
			PersonVo personVo=(PersonVo) getObjById(PersonVo.class, listDates.get(i));
			if (personVo !=null) {
				listNames.add(personVo.getName());
			}else {
				listNames.add("null");
			}
		}
		
		SeriesModel seriesModelOne=new SeriesModel("�ּ�", "spline");*//**�ּ�*//*
		SeriesModel seriesModelTwo=new SeriesModel("ԤԼ", "spline");*//**ԤԼ*//*
		SeriesModel seriesModelThree=new SeriesModel("����", "spline");*//**����*//*
		SeriesModel seriesModelFore=new SeriesModel("�ص�", "spline");*//**�ص�*//*
		
		*//**���ݴ���ʱ�������ʱ������ʱ��ļ���   ��Ҫ���ڼ���ƽ����*//*
		List<String> list=new ArrayList<String>();
		if (StringUtil.isBlank(type) ) {
			list=MyUtil.getBeforeSevenDayByNow();
			begindate=list.get(0);
			enddate=list.get((list.size()-1));
		}else {
			list=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(begindate, enddate));
		}
		
		if (listDates.size()>0) {//
			for (int i = 0; i < listDates.size(); i++) {
				double l=0;//�ּ��ۼ�ʱ���
				double y=0;//ԤԼ�ۼ�ʱ���
				double t=0;//�����ۼ�ʱ���
				double d=0;//�ص��ۼ�ʱ���
				String dateModel=listDates.get(i);     //��ʼһ��������Աѭ��
				*//**����ÿ���˵ķּ�*//*
				try {
					ps = ct.prepareStatement("select stepOneThrougtTime  from t_workflow_complain where acquiringDate  between '"+begindate+"' and '"+MyUtil.getSomeDateByDay(enddate, 1)+"' and complainStepOnePerson='"+dateModel+"'  and deleted=0 and projId='"+pid+"'  ");
					rs = ps.executeQuery();
					while(rs.next()){
						String str=rs.getString("stepOneThrougtTime");
						if (!StringUtil.isBlank(str)) {
							l +=Double.valueOf(str);
						}
					}
					if (l !=0) {
						seriesModelOne.getData().add(Double.valueOf(MyUtil.queryDoubleHourByMillSecond(l/list.size())));
					}else {
						seriesModelOne.getData().add(0);
					}
				} catch (Exception e) {
				}finally{
					super.close(ps, rs);
				}
				*//**����ÿ���˵�ԤԼ��ʱ��*//*
				try {
					ps = ct.prepareStatement("select complainOrderLong  from t_workflow_complain where acquiringDate  between '"+begindate+"' and '"+MyUtil.getSomeDateByDay(enddate,1)+"' and complainOrderPersonId='"+dateModel+"'  and deleted=0 and projId='"+pid+"'  ");
					rs = ps.executeQuery();
					while(rs.next()){
						String str=rs.getString("complainOrderLong");
						if (!StringUtil.isBlank(str)) {
							y +=Double.valueOf(str);
						}
					}
					if (y !=0) {
						seriesModelTwo.getData().add(Double.valueOf(MyUtil.queryDoubleHourByMillSecond(y/list.size())));
					}else {
						seriesModelTwo.getData().add(0);
					}
				} catch (Exception e) {
				}finally{
					super.close(ps, rs);
				}
				*//**����ÿ���˵Ĳ��Ե�ʱ��   *//*
				try {
					ps = ct.prepareStatement("select complainTestBeginDate,complainTestDate  from t_workflow_complain where complainTestBeginDate  between '"+begindate+"' and '"+MyUtil.getSomeDateByDay(enddate,1)+"' and  complainTestPersonId='"+dateModel+"'  and deleted=0 and projId='"+pid+"'  ");
			        rs = ps.executeQuery();
			        while(rs.next()){
			        	if (!StringUtil.isBlank(rs.getString("complainTestDate"))) {
			        		t +=MyUtil.queryMillSecondByTwoDate(rs.getString("complainTestBeginDate"), rs.getString("complainTestDate"));
						}
					}
			        if (t !=0) {
			        	seriesModelThree.getData().add(Double.valueOf(MyUtil.queryDoubleHourByMillSecond(t/list.size())));
					}else {
						seriesModelThree.getData().add(0);
					}
				} catch (Exception e) {
				}finally{
					super.close(ps, rs);
				}
				*//**����ÿ���˵Ļص���ʱ��   *//*
				try {
					ps = ct.prepareStatement("select complainBackStepDate,complainBackStepDateLast  from t_workflow_complain where complainBackStepDateLast  between '"+begindate+"' and '"+MyUtil.getSomeDateByDay(enddate,1)+"' and  conplainBackPersonId='"+dateModel+"'  and deleted=0 and projId='"+pid+"'  ");
					rs = ps.executeQuery();
					while(rs.next()){
						if (!StringUtil.isBlank(rs.getString("complainBackStepDateLast"))) {
							d +=MyUtil.queryMillSecondByTwoDate(rs.getString("complainBackStepDate"), rs.getString("complainBackStepDateLast"));
						}
					}
					if (d !=0) {
						seriesModelFore.getData().add(Double.valueOf(MyUtil.queryDoubleHourByMillSecond(d/list.size())));
					}else {
						seriesModelFore.getData().add(0);
					}
				} catch (Exception e) {
				}finally{
					super.close(ps, rs);
				}
				
			}
		}
		highChartModel.setCategories(listNames);
		highChartModel.getSeries().add(seriesModelOne);
		highChartModel.getSeries().add(seriesModelTwo);
		highChartModel.getSeries().add(seriesModelThree);
		highChartModel.getSeries().add(seriesModelFore);
		
		if (session.getAttribute(ApplicationConst.complainStatNumAvgTimeDay) !=null) {
			session.removeAttribute(ApplicationConst.complainStatNumAvgTimeDay);
	       }
	       session.setAttribute(ApplicationConst.complainStatNumAvgTimeDay, highChartModel);
			
		
		return highChartModel;
	}
	
	 
	*//*******************************************************************
	 * 						��Ա����
	 * ****************************************************************
	 *//*
	
	*//**
	 * ѡ��ʱ����ÿ����������
	 *//*
	public HeightchartModel personAnalyseNum(String pid,String begindate,String enddate,List<String> listPersonList,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("SSV", "column");
		SeriesModel seriesModelTwo=new SeriesModel("Test", "column");
		SeriesModel seriesModelThree=new SeriesModel("Grid", "column");
		SeriesModel seriesModelFore=new SeriesModel("Kpi", "column");
		SeriesModel seriesModelFive=new SeriesModel("Extra", "column");
		SeriesModel seriesModelSix=new SeriesModel("All", "spline");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate)) {*//**���û�д���ʱ�� ��ô����Ĭ�ϵ�������   ����Ļ����ǲ��ô��������*//*
			//enddate=MyUtil.getNowDate("yyyy-MM");
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,-2);
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}else {
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(begindate,0);
			//enddate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,0);
		}
		*//***ǰ��������ʾʱ���*//*
		highChartModel.setBeginDate(begindate);
		highChartModel.setEndDate(enddate);
		
		int num=listPersonList.size();
	       if (num>0) {
				for (int i = 0; i < num; i++) {*//**��ʼѭ������*//*
					*//**ÿ���˵��������*//*
					String personName=listPersonList.get(i);
					
					seriesModelOne.getData().add(getRowsCount("select distinct(testdate) from dyjl where  deleted='0' and projId='"+pid+"' and  (person1='"+personName+"' or person2='"+personName+"')  and testdate between '"+begindate+"' and '"+enddate+"' "));
					seriesModelTwo.getData().add(getRowsCount("select distinct(testTime) from grid_net_test where  deleted='0' and projId='"+pid+"' and  testPerson='"+personName+"'  and testTime between '"+begindate+"' and '"+enddate+"' "));
					seriesModelThree.getData().add(getRowsCount("select distinct(proTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"' and proPeoson='"+personName+"' and proTime between '"+begindate+"' and '"+enddate+"' union select distinct(proOkTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"'  and proPeoson='"+personName+"' and proOkTime between '"+begindate+"' and '"+enddate+"' "));
					seriesModelFore.getData().add(getRowsCount("select distinct(switchdate) as datemodel from kpi_net_switch  where  deleted='0' and projId='"+pid+"' and  engineer='"+personName+"'  and switchdate between '"+begindate+"' and '"+enddate+"' union select distinct(rrcswitchdate) as datemodel from kpi_net_rrc  where  deleted='0' and projId='"+pid+"' and  rrcengineer='"+personName+"'  and rrcswitchdate between '"+begindate+"' and '"+enddate+"' union  select distinct(rabswitchdate) as datemodel from kpi_net_rab  where  deleted='0' and projId='"+pid+"' and  rabengineer='"+personName+"'  and rabswitchdate between '"+begindate+"' and '"+enddate+"' union select distinct(unswitchdate) as datemodel from kpi_net_unline  where  deleted='0' and projId='"+pid+"' and  unengineer='"+personName+"'  and unswitchdate between '"+begindate+"' and '"+enddate+"' "));
					seriesModelFive.getData().add(getRowsCount("select distinct(timeDate) as datemodel from extra_request  where  deleted='0'  and projId='"+pid+"' and principal='"+personName+"'  and timeDate between '"+begindate+"' and '"+enddate+"' union  select distinct(extTime) as datemodel from grid_net_extra  where  deleted='0'   and projId='"+pid+"' and  extPerson='"+personName+"'  and extTime between '"+begindate+"' and '"+enddate+"' union select distinct(extraweek) as datemodel from kpi_net_extea  where  deleted='0' and projId='"+pid+"' and  extraPerson='"+personName+"'  and extraweek between '"+begindate+"' and '"+enddate+"' "));
					
					StringBuffer sb=new StringBuffer();
					sb.append("select distinct(testdate) as datemodel  from dyjl where  deleted='0' and projId='"+pid+"' and  (person1='"+personName+"' or person2='"+personName+"')  and testdate between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(testTime) as datemodel  from grid_net_test where  deleted='0' and projId='"+pid+"' and  testPerson='"+personName+"'  and testTime between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(proTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"' and proPeoson='"+personName+"' and proTime between '"+begindate+"' and '"+enddate+"' union select distinct(proOkTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"'  and proPeoson='"+personName+"' and proOkTime between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(switchdate) as datemodel from kpi_net_switch  where  deleted='0' and projId='"+pid+"' and  engineer='"+personName+"'  and switchdate between '"+begindate+"' and '"+enddate+"' union select distinct(rrcswitchdate) as datemodel from kpi_net_rrc  where  deleted='0' and projId='"+pid+"' and  rrcengineer='"+personName+"'  and rrcswitchdate between '"+begindate+"' and '"+enddate+"' union  select distinct(rabswitchdate) as datemodel from kpi_net_rab  where  deleted='0' and projId='"+pid+"' and  rabengineer='"+personName+"'  and rabswitchdate between '"+begindate+"' and '"+enddate+"' union select distinct(unswitchdate) as datemodel from kpi_net_unline  where  deleted='0' and projId='"+pid+"' and  unengineer='"+personName+"'  and unswitchdate between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(timeDate) as datemodel from extra_request  where  deleted='0'  and projId='"+pid+"' and principal='"+personName+"'  and timeDate between '"+begindate+"' and '"+enddate+"' union  select distinct(extTime) as datemodel from grid_net_extra  where  deleted='0'   and projId='"+pid+"' and  extPerson='"+personName+"'  and extTime between '"+begindate+"' and '"+enddate+"' union select distinct(extraweek) as datemodel from kpi_net_extea  where  deleted='0' and projId='"+pid+"' and  extraPerson='"+personName+"'  and extraweek between '"+begindate+"' and '"+enddate+"'  ");
					seriesModelSix.getData().add(getRowsCount(sb.toString()));
					String personName=listPersonList.get(i);
					seriesModelOne.getData().add(getRowsCount("select distinct(testdate) from dyjl where  deleted='0' and projId='"+pid+"' and  (person1='"+personName+"' or person2='"+personName+"')  and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' "));
					seriesModelTwo.getData().add(getRowsCount("select distinct(testTime) from grid_net_test where  deleted='0' and projId='"+pid+"' and  testPerson='"+personName+"'  and date_format(testTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' "));
					seriesModelThree.getData().add(getRowsCount("select distinct(proTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"' and proPeoson='"+personName+"' and date_format(proTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(proOkTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"'  and proPeoson='"+personName+"' and date_format(proOkTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' "));
					seriesModelFore.getData().add(getRowsCount("select distinct(switchdate) as datemodel from kpi_net_switch  where  deleted='0' and projId='"+pid+"' and  engineer='"+personName+"'  and date_format(switchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(rrcswitchdate) as datemodel from kpi_net_rrc  where  deleted='0' and projId='"+pid+"' and  rrcengineer='"+personName+"'  and date_format(rrcswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  select distinct(rabswitchdate) as datemodel from kpi_net_rab  where  deleted='0' and projId='"+pid+"' and  rabengineer='"+personName+"'  and date_format(rabswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(unswitchdate) as datemodel from kpi_net_unline  where  deleted='0' and projId='"+pid+"' and  unengineer='"+personName+"'  and date_format(unswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' "));
					seriesModelFive.getData().add(getRowsCount("select distinct(timeDate) as datemodel from extra_request  where  deleted='0'  and projId='"+pid+"' and principal='"+personName+"'  and date_format(timeDate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  select distinct(extTime) as datemodel from grid_net_extra  where  deleted='0'   and projId='"+pid+"' and  extPerson='"+personName+"'  and date_format(extTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(extraweek) as datemodel from kpi_net_extea  where  deleted='0' and projId='"+pid+"' and  extraPerson='"+personName+"'  and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"' "));
					
					StringBuffer sb=new StringBuffer();
					sb.append("select distinct(testdate) as datemodel  from dyjl where  deleted='0' and projId='"+pid+"' and  (person1='"+personName+"' or person2='"+personName+"')  and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(testTime) as datemodel  from grid_net_test where  deleted='0' and projId='"+pid+"' and  testPerson='"+personName+"'  and date_format(testTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(proTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"' and proPeoson='"+personName+"' and date_format(proTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(proOkTime) as datemodel from grid_net_problem where  deleted='0' and projId='"+pid+"'  and proPeoson='"+personName+"' and date_format(proOkTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(switchdate) as datemodel from kpi_net_switch  where  deleted='0' and projId='"+pid+"' and  engineer='"+personName+"'  and date_format(switchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(rrcswitchdate) as datemodel from kpi_net_rrc  where  deleted='0' and projId='"+pid+"' and  rrcengineer='"+personName+"'  and date_format(rrcswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  select distinct(rabswitchdate) as datemodel from kpi_net_rab  where  deleted='0' and projId='"+pid+"' and  rabengineer='"+personName+"'  and date_format(rabswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(unswitchdate) as datemodel from kpi_net_unline  where  deleted='0' and projId='"+pid+"' and  unengineer='"+personName+"'  and date_format(unswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
					sb.append("select distinct(timeDate) as datemodel from extra_request  where  deleted='0'  and projId='"+pid+"' and principal='"+personName+"'  and date_format(timeDate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  select distinct(extTime) as datemodel from grid_net_extra  where  deleted='0'   and projId='"+pid+"' and  extPerson='"+personName+"'  and date_format(extTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union select distinct(extraweek) as datemodel from kpi_net_extea  where  deleted='0' and projId='"+pid+"' and  extraPerson='"+personName+"'  and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"'  ");
					seriesModelSix.getData().add(getRowsCount(sb.toString()));
				}
		  }
	       highChartModel.setCategories(listPersonList);
	       highChartModel.getSeries().add(seriesModelOne);
	       highChartModel.getSeries().add(seriesModelTwo);
	       highChartModel.getSeries().add(seriesModelThree);
	       highChartModel.getSeries().add(seriesModelFore);
	       highChartModel.getSeries().add(seriesModelFive);
	       highChartModel.getSeries().add(seriesModelSix);
	       *//***ÿ�������������뻺��*//*
	       if (session.getAttribute(ApplicationConst.personAnalyseNum) !=null) {
			session.removeAttribute(ApplicationConst.personAnalyseNum);
	       }
	       session.setAttribute(ApplicationConst.personAnalyseNum, highChartModel);
		return highChartModel;
	}
	
	*//**
	 * �����µ���Ա��
	 *//*
	public void goPersonAnalysePageThreeMonth(HttpSession session,String pid,String begindate,String enddate) throws Exception {
		List<String> listDates=new ArrayList<String>();*//**��ѯ��¼���е���Ա*//*
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate))  {
			//enddate=MyUtil.getNowDate("yyyy-MM");
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,-2);
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}//else {
			//enddate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,0);
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(begindate,0);
		//}
		*//***�������  10��������Ĳ�ͬ��*//*
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select distinct(person1) as person from dyjl where  deleted='0' and projId='"+pid+"' and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(person2) as person from dyjl where  deleted='0' and projId='"+pid+"' and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(testPerson) as person from grid_net_test where  deleted='0' and projId='"+pid+"'   and date_format(testTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(proPeoson) as person  from grid_net_problem where  deleted='0'  and projId='"+pid+"'   and date_format(proTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(engineer) as person from kpi_net_switch  where  deleted='0'  and projId='"+pid+"'  and date_format(switchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(rrcengineer) as person from kpi_net_rrc  where  deleted='0'   and projId='"+pid+"'  and date_format(rrcswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(rabengineer) as person from kpi_net_rab  where  deleted='0'  and projId='"+pid+"'  and date_format(rabswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(unengineer) as person from kpi_net_unline  where  deleted='0'  and projId='"+pid+"'  and date_format(unswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(principal) as person from extra_request  where  deleted='0'   and projId='"+pid+"'  and date_format(timeDate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(extPerson) as person from grid_net_extra  where  deleted='0' and projId='"+pid+"'   and date_format(extTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(extraPerson) as person from kpi_net_extea  where  deleted='0'  and projId='"+pid+"'  and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"' ");
			
			
			sql.append("select distinct(person1) as person from dyjl where  deleted='0' and projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(person2) as person from dyjl where  deleted='0' and projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(testPerson) as person from grid_net_test where  deleted='0' and projId='"+pid+"'   and testTime between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(proPeoson) as person  from grid_net_problem where  deleted='0'  and projId='"+pid+"'   and proTime between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(engineer) as person from kpi_net_switch  where  deleted='0'  and projId='"+pid+"'  and switchdate between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(rrcengineer) as person from kpi_net_rrc  where  deleted='0'   and projId='"+pid+"'  and rrcswitchdate between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(rabengineer) as person from kpi_net_rab  where  deleted='0'  and projId='"+pid+"'  and rabswitchdate between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append("select distinct(unengineer) as person from kpi_net_unline  where  deleted='0'  and projId='"+pid+"'  and unswitchdate between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(principal) as person from extra_request  where  deleted='0'   and projId='"+pid+"'  and timeDate between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(extPerson) as person from grid_net_extra  where  deleted='0' and projId='"+pid+"'   and extTime between '"+begindate+"' and '"+enddate+"' union ");
			sql.append("select distinct(extraPerson) as person from kpi_net_extea  where  deleted='0'  and projId='"+pid+"'  and extraweek between '"+begindate+"' and '"+enddate+"' ");
			
			ps = ct.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				String name=rs.getString("person");
				if (!StringUtil.isBlank(name)) {
					listDates.add(name);
				}
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		session.removeAttribute(ApplicationConst.highchartPersonList);
		session.setAttribute(ApplicationConst.highchartPersonList, listDates);
	}
	*//**
	 * ssv��Ա������ѯ��
	 *//*
	public List<String> goSsvPersonAnalyseNum(HttpSession session,String pid,String begindate,String enddate) throws Exception {
		List<String> listDates=new ArrayList<String>();*//**��ѯ��¼���е���Ա*//*
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate))  {
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select distinct(person2) as person from dyjl  where projId='"+pid+"'  and  deleted='0' and testDate between '"+begindate+"' and '"+enddate+"' union  ");
			sql.append(" select distinct(person1) as person from dyjl  where projId='"+pid+"'  and  deleted='0' and testDate between '"+begindate+"' and '"+enddate+"' ");
			ps = ct.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				String name=rs.getString("person");
				if (!StringUtil.isBlank(name)) {
					listDates.add(name);
				}
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		return listDates;
	}
	*//**
	 * grid��Ա������ѯ��
	 *//*
	public List<String> goGridPersonAnalyseNum(HttpSession session,String pid,String begindate,String enddate) throws Exception {
		List<String> listDates=new ArrayList<String>();*//**��ѯ��¼���е���Ա*//*
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate))  {
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select distinct(proPeoson) as person from grid_net_problem where projId='"+pid+"' and   deleted='0' and (proOkTime is null or length(proOkTime)<=0 or (proOkTime is not null and proOkTime between '"+begindate+"' and '"+enddate+"')) and (proTime between '"+begindate+"' and '"+enddate+"') ");
			ps = ct.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				String name=rs.getString("person");
				if (!StringUtil.isBlank(name)) {
					listDates.add(name);
				}
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		return listDates;
	}
	*//**
	 * ѡ��ʱ����ÿ������
	 * @throws Exception 
	 *//*
	public HeightchartModel personAnalyseNumByDay(String pid, String begindate,String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("SSV", "column");
		SeriesModel seriesModelTwo=new SeriesModel("Test", "column");
		SeriesModel seriesModelThree=new SeriesModel("Grid", "column");
		SeriesModel seriesModelFore=new SeriesModel("Kpi", "column");
		SeriesModel seriesModelFive=new SeriesModel("Extra", "column");
		SeriesModel seriesModelSix=new SeriesModel("All", "spline");
		
		List<String> listDtes=new ArrayList<String>();
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate)) {*//**���û�д���ʱ�� ��ô����Ĭ�ϵ�������   ����Ļ����ǲ��ô��������*//*
			listDtes=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(MyUtil.getDateBeforSevenDay(-7),MyUtil.getDateBeforSevenDay(-1)));
		}else {
			listDtes=MyUtil.listDateToStr(MyUtil.getDateBetweenBeginAndEnd(begindate,enddate));
		}
		*//***ǰ��������ʾʱ���*//*
		int num=listDtes.size();
		
       if (num>0) {
			for (int i = 0; i < num; i++) {*//**��ʼѭ������*//*
				*//**һ����ѭ����������*//*
				String perDay=listDtes.get(i);
				seriesModelOne.getData().add(getRowsCount("select distinct(person1) from dyjl where  projId='"+pid+"' and   testdate = '"+perDay+"' and   deleted='0' and person1 is not null and length(person1)>0 union select distinct(person2) from dyjl where  projId='"+pid+"' and   testdate = '"+perDay+"' and   deleted='0' and person2 is not null and length(person2)>0 "));
				seriesModelTwo.getData().add(getRowsCount("select distinct(testPerson) from grid_net_test where projId='"+pid+"' and  testTime = '"+perDay+"' and   deleted='0' "));
				seriesModelThree.getData().add(getRowsCount("select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and (proTime = '"+perDay+"' or proOkTime = '"+perDay+"' ) and   deleted='0' "));
				seriesModelFore.getData().add(getRowsCount(" select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and switchdate ='"+perDay+"' and  deleted='0' union select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and  rrcswitchdate='"+perDay+"' and deleted='0' union select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and rabswitchdate ='"+perDay+"' and  deleted='0' union select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and  unswitchdate ='"+perDay+"' and deleted='0' union select distinct(extraPerson)  from kpi_net_extea  where  projId='"+pid+"' and extraweek = '"+perDay+"' and deleted='0' "));
				seriesModelFive.getData().add(getRowsCount(" select distinct(principal)  from extra_request  where projId='"+pid+"' and timeDate = '"+perDay+"' and deleted='0' union select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'  and   extTime ='"+perDay+"' and deleted='0' union select distinct(extraPerson)  from kpi_net_extea  where projId='"+pid+"' and  extraweek ='"+perDay+"' and  deleted='0' "));
				
				StringBuffer sb=new StringBuffer();
				sb.append("select distinct(person1) from dyjl where  projId='"+pid+"' and   testdate = '"+perDay+"' and   deleted='0' union ");
				sb.append("select distinct(person2) from dyjl where  projId='"+pid+"' and   testdate = '"+perDay+"' and   deleted='0' union ");
				sb.append("select distinct(testPerson) from grid_net_test where projId='"+pid+"' and  testTime = '"+perDay+"' and   deleted='0' union ");
				sb.append("select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and (proTime = '"+perDay+"' or proOkTime = '"+perDay+"' ) and   deleted='0' union ");
				sb.append("select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and switchdate ='"+perDay+"' and  deleted='0' union ");
				sb.append("select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and  rrcswitchdate='"+perDay+"' and deleted='0' union ");
				sb.append("select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and rabswitchdate ='"+perDay+"' and  deleted='0' union ");
				sb.append("select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and  unswitchdate ='"+perDay+"' and deleted='0' union ");
				sb.append("select distinct(extraPerson)  from kpi_net_extea  where  projId='"+pid+"' and extraweek = '"+perDay+"' and deleted='0' union ");
				sb.append("select distinct(principal)  from extra_request  where projId='"+pid+"' and timeDate = '"+perDay+"' and deleted='0' union ");
				sb.append("select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'   and   extTime ='"+perDay+"' and deleted='0'  ");
				seriesModelSix.getData().add(getRowsCount(sb.toString()));
				//seriesModelSix.getData().add(0);
			}
			highChartModel.setCategories(listDtes);
			highChartModel.getSeries().add(seriesModelOne);
			highChartModel.getSeries().add(seriesModelTwo);
			highChartModel.getSeries().add(seriesModelThree);
			highChartModel.getSeries().add(seriesModelFore);
			highChartModel.getSeries().add(seriesModelFive);
			highChartModel.getSeries().add(seriesModelSix);
		}
       *//***ÿ����������뻺��*//*
       if (session.getAttribute(ApplicationConst.personAnalyseNumByDay) !=null) {
		session.removeAttribute(ApplicationConst.personAnalyseNumByDay);
       }
       session.setAttribute(ApplicationConst.personAnalyseNumByDay, highChartModel);
	
		return highChartModel;
	}
	*//**
	 * ʱ��θ���Ŀ������  �˴�
	 *//*
	public HeightchartModel personAnalyseNumByPro(String pid, String begindate,String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("�˴�", "column");
		
		List<String> listDtes=new ArrayList<String>();
		listDtes.add("SSV");
		listDtes.add("Test");
		listDtes.add("Grid");
		listDtes.add("Kpi");
		listDtes.add("Extra");
		listDtes.add("All");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate))  {
			//enddate=MyUtil.getNowDate("yyyy-MM");
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,-2);
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}else {
			//enddate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,0);
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(begindate,0);
		}
		
				seriesModelOne.getData().add(getRowsCount(" select distinct(person1) from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person1 is not null and person1 <> '' union select distinct(person2) from dyjl where  projId='"+pid+"' and   testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person2 is not null and person2 <> '' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(testPerson) from grid_net_test where projId='"+pid+"' and testTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' and testPerson is not null and testPerson <> '' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and((proTime between '"+begindate+"' and '"+enddate+"') or (proOkTime between '"+begindate+"' and '"+enddate+"')) and   deleted='0' and proPeoson is not null and proPeoson <> '' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and switchdate between '"+begindate+"' and '"+enddate+"'  and  deleted='0' and engineer is not null and engineer <> '' union select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and rrcswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and rrcengineer is not null and rrcengineer <> '' union select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and rabswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and rabengineer is not null and rabengineer <>'' union select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and unswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and unengineer is not null and unengineer <> ''  "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(principal)  from extra_request  where projId='"+pid+"' and timeDate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and principal is not null and principal <> '' union select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'   and extTime between '"+begindate+"' and '"+enddate+"'   and deleted='0' and extPerson is not null and extPerson <> '' union select distinct(extraPerson)  from kpi_net_extea  where projId='"+pid+"'    and extraweek between '"+begindate+"' and '"+enddate+"'   and deleted='0' and extraPerson is not null  and extraPerson <> '' "));
				
				StringBuffer sb=new StringBuffer();
				sb.append(" select distinct(person1) from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person1 is not null and person1 <> '' union ");
				sb.append(" select distinct(person2) from dyjl where  projId='"+pid+"' and   testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person2 is not null and person2 <> '' union ");
				sb.append(" select distinct(testPerson) from grid_net_test where projId='"+pid+"' and testTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' and testPerson is not null and testPerson <> '' union ");
				sb.append(" select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and ((proTime between '"+begindate+"' and '"+enddate+"') or (proOkTime between '"+begindate+"' and '"+enddate+"')) and   deleted='0' and proPeoson is not null and proPeoson <> '' union ");
				sb.append(" select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and switchdate between '"+begindate+"' and '"+enddate+"'  and  deleted='0' and engineer is not null and engineer <> '' union ");
				sb.append(" select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and rrcswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and rrcengineer is not null and rrcengineer <> '' union ");
				sb.append(" select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and rabswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and rabengineer is not null and rabengineer <> '' union ");
				sb.append(" select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and unswitchdate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and unengineer is not null and unengineer <> ''  union ");
				sb.append(" select distinct(extraPerson)  from kpi_net_extea  where  projId='"+pid+"' and extraweek between '"+begindate+"' and '"+enddate+"'   and deleted='0' and extraPerson is not null and extraPerson <> '' union ");
				sb.append(" select distinct(principal)  from extra_request  where projId='"+pid+"' and timeDate between '"+begindate+"' and '"+enddate+"'   and deleted='0' and principal is not null and principal <> '' union ");
				sb.append(" select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'   and extTime between '"+begindate+"' and '"+enddate+"'   and deleted='0' and extPerson is not null and extPerson <> '' union ");
				sb.append(" select distinct(extraPerson)  from kpi_net_extea  where projId='"+pid+"'    and extraweek between '"+begindate+"' and '"+enddate+"' and extraPerson is not null and extraPerson <> ''   and deleted='0'  ");
				seriesModelOne.getData().add(getRowsCount(sb.toString()));
				
				seriesModelOne.getData().add(getRowsCount(" select distinct(person1) from dyjl where  projId='"+pid+"' and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' union select distinct(person2) from dyjl where  projId='"+pid+"' and   date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(testPerson) from grid_net_test where projId='"+pid+"' and date_format(testTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and((date_format(proTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"') or (date_format(proOkTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"')) and   deleted='0' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and date_format(switchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'  and  deleted='0' union select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and date_format(rrcswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and date_format(rabswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and date_format(unswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union select distinct(extraPerson)  from kpi_net_extea  where  projId='"+pid+"' and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' "));
				seriesModelOne.getData().add(getRowsCount(" select distinct(principal)  from extra_request  where projId='"+pid+"' and date_format(timeDate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'   and date_format(extTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union select distinct(extraPerson)  from kpi_net_extea  where projId='"+pid+"'    and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0'  "));
				
				StringBuffer sb=new StringBuffer();
				sb.append(" select distinct(person1) from dyjl where  projId='"+pid+"' and date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' union ");
				sb.append(" select distinct(person2) from dyjl where  projId='"+pid+"' and   date_format(testdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' union ");
				sb.append(" select distinct(testPerson) from grid_net_test where projId='"+pid+"' and date_format(testTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"' and   deleted='0' union ");
				sb.append(" select distinct(proPeoson) from grid_net_problem where projId='"+pid+"' and ((date_format(proTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"') or (date_format(proOkTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"')) and   deleted='0' union ");
				sb.append(" select distinct(engineer)  from kpi_net_switch  where  projId='"+pid+"' and date_format(switchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'  and  deleted='0' union ");
				sb.append(" select distinct(rrcengineer) from kpi_net_rrc  where projId='"+pid+"' and date_format(rrcswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union ");
				sb.append(" select distinct(rabengineer)  from kpi_net_rab  where projId='"+pid+"'  and date_format(rabswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union ");
				sb.append(" select distinct(unengineer) from kpi_net_unline  where  projId='"+pid+"' and date_format(unswitchdate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0'  union ");
				sb.append(" select distinct(extraPerson)  from kpi_net_extea  where  projId='"+pid+"' and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union ");
				sb.append(" select distinct(principal)  from extra_request  where projId='"+pid+"' and date_format(timeDate,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union ");
				sb.append(" select distinct(extPerson)  from grid_net_extra  where  projId='"+pid+"'   and date_format(extTime,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0' union ");
				sb.append(" select distinct(extraPerson)  from kpi_net_extea  where projId='"+pid+"'    and date_format(extraweek,'%Y-%m') between '"+begindate+"' and '"+enddate+"'   and deleted='0'  ");
				seriesModelOne.getData().add(getRowsCount(sb.toString()));
				
				
			highChartModel.setCategories(listDtes);
			highChartModel.getSeries().add(seriesModelOne);
			  *//***�˴�*//*
		       if (session.getAttribute(ApplicationConst.personAnalyseNumByPro) !=null) {
				session.removeAttribute(ApplicationConst.personAnalyseNumByPro);
		       }
		       session.setAttribute(ApplicationConst.personAnalyseNumByPro, highChartModel);
			
		return highChartModel;
	}
	*//**
	 * ����
	 * @throws Exception 
	 *//*
	public HeightchartModel personAnalyseNumByPersonDay(String pid,String begindate, String enddate,HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("����", "column");
		
		List<String> listDtes=new ArrayList<String>();
		listDtes.add("SSV");
		listDtes.add("Test");
		listDtes.add("Grid");
		listDtes.add("Kpi");
		listDtes.add("Extra");
		listDtes.add("All");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate))  {
			//enddate=MyUtil.getNowDate("yyyy-MM");
			//begindate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,-2);
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}else {
			//enddate=MyUtil.getDateBeforeThreeOnlyMonths(enddate,0);
		//	begindate=MyUtil.getDateBeforeThreeOnlyMonths(begindate,0);
		}
		
				seriesModelOne.getData().add(getRowsCount(" select person1,testdate from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person1 is not null and length(person1) >0 group by person1,testdate union select person2,testdate from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' and person2 is not null and length(person2) >0 group by person2,testdate "));
				seriesModelOne.getData().add(getRowsCount(" select testPerson,testTime from grid_net_test where projId='"+pid+"' and testTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by testPerson,testTime  "));
				seriesModelOne.getData().add(getRowsCount(" select proPeoson,proTime from grid_net_problem  where projId='"+pid+"' and proTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by proPeoson,proTime union select proPeoson,proOkTime from grid_net_problem  where projId='"+pid+"' and proOkTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by proPeoson,proOkTime "));
				seriesModelOne.getData().add(getRowsCount(" select engineer,switchdate from kpi_net_switch  where projId='"+pid+"' and switchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by engineer,switchdate union select rrcengineer,rrcswitchdate from kpi_net_rrc  where projId='"+pid+"' and rrcswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by rrcengineer,rrcswitchdate union select rabengineer,rabswitchdate from kpi_net_rab  where projId='"+pid+"' and rabswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by rabengineer,rabswitchdate union select unengineer,unswitchdate from kpi_net_unline  where projId='"+pid+"' and unswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by unengineer,unswitchdate union select extraPerson,extraweek from kpi_net_extea  where projId='"+pid+"' and extraweek between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extraPerson,extraweek "));
				seriesModelOne.getData().add(getRowsCount(" select principal,timeDate from extra_request  where projId='"+pid+"' and timeDate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by principal,timeDate union select extPerson,extTime from grid_net_extra  where projId='"+pid+"' and extTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extPerson,extTime union select extraPerson,extraweek from kpi_net_extea  where projId='"+pid+"' and extraweek between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extraPerson,extraweek  "));
				
				
				StringBuffer sb=new StringBuffer();
				sb.append(" select person1,testdate from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by person1,testdate union ");
				sb.append(" select person2,testdate from dyjl where  projId='"+pid+"' and testdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by person2,testdate union ");
				sb.append(" select testPerson,testTime from grid_net_test where projId='"+pid+"' and testTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by testPerson,testTime union ");
				sb.append(" select proPeoson,proTime from grid_net_problem  where projId='"+pid+"' and proTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by proPeoson,proTime union ");
				sb.append(" select proPeoson,proOkTime from grid_net_problem  where projId='"+pid+"' and proOkTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by proPeoson,proOkTime union ");
				sb.append(" select engineer,switchdate from kpi_net_switch  where projId='"+pid+"' and switchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by engineer,switchdate union ");
				sb.append(" select rrcengineer,rrcswitchdate from kpi_net_rrc  where projId='"+pid+"' and rrcswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by rrcengineer,rrcswitchdate union ");
				sb.append(" select rabengineer,rabswitchdate from kpi_net_rab  where projId='"+pid+"' and rabswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by rabengineer,rabswitchdate union ");
				sb.append(" select unengineer,unswitchdate from kpi_net_unline  where projId='"+pid+"' and unswitchdate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by unengineer,unswitchdate union ");
				sb.append(" select extraPerson,extraweek from kpi_net_extea  where projId='"+pid+"' and extraweek between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extraPerson,extraweek union ");
				sb.append(" select principal,timeDate from extra_request  where projId='"+pid+"' and timeDate between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by principal,timeDate union ");
				sb.append(" select extPerson,extTime from grid_net_extra  where projId='"+pid+"' and extTime between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extPerson,extTime union ");
				sb.append(" select extraPerson,extraweek from kpi_net_extea  where projId='"+pid+"' and extraweek between '"+begindate+"' and '"+enddate+"' and   deleted='0' group by extraPerson,extraweek ");
				seriesModelOne.getData().add(getRowsCount(sb.toString()));
			highChartModel.setCategories(listDtes);
			highChartModel.getSeries().add(seriesModelOne);
			
				*//***����*//*
		       if (session.getAttribute(ApplicationConst.personAnalyseNumByPersonDay) !=null) {
				session.removeAttribute(ApplicationConst.personAnalyseNumByPersonDay);
		        }
		       session.setAttribute(ApplicationConst.personAnalyseNumByPersonDay, highChartModel);
			
		return highChartModel;
	}
	*//**
	 * ��Ա��������
	 * @throws IOException 
	 *//*
	public void exportPersonAnalyse(String[] headersOne, String[] headersTwo,
			String[] headersThree, String[] headersFore,String[] headersFive,
			String[] headersSix, String[] headersSeven,Collection<HeightChartExportModel> listOne, Collection<HeightChartExportModel> listTwo,
			Collection<HeightChartExportModel> listThree, Collection<HeightChartExportModel> listFore,Collection<HeightChartExportModel> listFive,
			Collection<HeightChartExportModel> listSix,Collection<HeightChartExportModel> listSeven, String chartOneName, String chartTwoName, String chartThreeName,
			String chartForeName,String chartFiveName, String chartSixName, String chartSenvenName, String title, HttpServletResponse response,String parentPath, String excelTitle) throws IOException {
		OutputStream out = response.getOutputStream();
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet(title);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
		sheet.setDefaultColumnWidth((int) 12);
		// ����һ����ʽ ����ר��
		HSSFCellStyle style = workbook.createCellStyle();
		// ������Щ��ʽ
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ����һ������
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ������Ӧ�õ���ǰ����ʽ
		style.setFont(font);

		// ���ɲ�������һ����ʽ
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ������һ������
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		style2.setFont(font2);

		// ���ɲ�������һ����ʽ ����
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle styleDate = workbook.createCellStyle();
		styleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleDate.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleDate.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleDate.setDataFormat(format.getFormat("yyyy-MM-dd"));
		// ������һ������
		HSSFFont fontDate = workbook.createFont();
		fontDate.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		styleDate.setFont(fontDate);

		
		// ��ע����ɫ
		HSSFCellStyle stylebg = workbook.createCellStyle();
		// ������Щ��ʽ
		stylebg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
		stylebg.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		stylebg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		stylebg.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		stylebg.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		
		
		// ����һ����ͼ�Ķ���������
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		*//**********************����ѭ����ʼ***************************************//*
		 int index=0;
		 sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));//���򣺺ϲ���0�еĵ�0�е���9��      ��ʼ����ֹ�� ��ʼ�� ��ֹ��
		 HSSFRow row1 = sheet.createRow(index++);
		 row1.setHeightInPoints(40);  
		 HSSFCell cell1 = row1.createCell((int)0);
	     cell1.setCellStyle(style);
	     cell1.setCellValue(excelTitle);
		//ͼ
		if (!StringUtil.isBlank(chartOneName)) {
        	BufferedImage bufferImg3 = null;
        	ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
        	File file=new File(parentPath+"\\"+chartOneName);
        	if (file.exists()) {
        		try {
        			bufferImg3 = ImageIO.read(file);
            		ImageIO.write(bufferImg3,getPictureType(chartOneName), byteArrayOut3);
            		// ͼƬһ��������Ԫ��B2��  �м�����������
            		HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);//�� ��
            		// ����ͼƬ�м�����������  
            		if (getPictureType(chartOneName).equals("png")) {
            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
            		}else {
            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
            		}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
        	}
        	
        	//������ע����
        	 sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
    		 HSSFRow rowRemark = sheet.createRow(index);
    		 cell1 = rowRemark.createCell((int)7);
    	     cell1.setCellStyle(stylebg);
    	     cell1.setCellValue("��ע����:");
        	index=index+16;
        }
		
		HSSFRow row ;
		// ������������
		 row = sheet.createRow(index++);
		for (short i = 0; i < headersOne.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersOne[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> it = listOne.iterator();
		while (it.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = it.next();

			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());

			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
		}
		index=index+2;
		*//**********************����ѭ������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
				if (!StringUtil.isBlank(chartTwoName)) {
		        	BufferedImage bufferImg3 = null;
		        	ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
		        	File file=new File(parentPath+"\\"+chartTwoName);
		        	if (file.exists()) {
		        		try {
		        			bufferImg3 = ImageIO.read(file);
		            		ImageIO.write(bufferImg3,getPictureType(chartTwoName), byteArrayOut3);
		            		// ͼƬһ��������Ԫ��B2��  �м�����������
		            		HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
		            		// ����ͼƬ�м�����������  
		            		if (getPictureType(chartTwoName).equals("png")) {
		            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
		            		}else {
		            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
		            		}
						} catch (Exception e) {
						}finally{
							if (file.exists()) {//ɾ���ļ�
								file.delete();
							}
						}
		        	}
		        	//������ע����
		        	 sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
		    		 HSSFRow rowRemark = sheet.createRow(index);
		    		 cell1 = rowRemark.createCell((int)7);
		    	     cell1.setCellStyle(stylebg);
		    	     cell1.setCellValue("��ע����:");
		    	     index=index+16;
		        }
				// ������������
				 row = sheet.createRow(index++);
				for (short i = 0; i < headersTwo.length; i++) {
					HSSFCell cell = row.createCell((int) i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headersTwo[i]);
					cell.setCellValue(text);
				}
				//����
				Iterator<HeightChartExportModel> itTwo = listTwo.iterator();
				while (itTwo.hasNext()) {
					HSSFCell cell;
					row = sheet.createRow(index++);
					HeightChartExportModel t = itTwo.next();
					
					cell = row.createCell((int) 0);
					cell.setCellStyle(style2);
					cell.setCellValue(t.getType());

					cell = row.createCell((int) 1);
					cell.setCellStyle(style2);
					cell.setCellValue(t.getSsv().toString());
				}
				index=index+2;
				*//**********************����ѭ�������***************************************//*
				*//**********************����ѭ����ʼ***************************************//*
				//ͼ
						if (!StringUtil.isBlank(chartThreeName)) {
				        	BufferedImage bufferImg3 = null;
				        	ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
				        	File file=new File(parentPath+"\\"+chartThreeName);
				        	if (file.exists()) {
				        		try {
				        			bufferImg3 = ImageIO.read(file);
				            		ImageIO.write(bufferImg3,getPictureType(chartThreeName), byteArrayOut3);
				            		// ͼƬһ��������Ԫ��B2��  �м�����������
				            		HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
				            		// ����ͼƬ�м�����������  
				            		if (getPictureType(chartThreeName).equals("png")) {
				            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
				            		}else {
				            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
				            		}
								} catch (Exception e) {
								}finally{
									if (file.exists()) {//ɾ���ļ�
										file.delete();
									}
								}
				        	}
				        	//������ע����
				        	 sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
				    		 HSSFRow rowRemark = sheet.createRow(index);
				    		 cell1 = rowRemark.createCell((int)7);
				    	     cell1.setCellStyle(stylebg);
				    	     cell1.setCellValue("��ע����:");
				    	     index=index+16;
				        }
						// ������������
						 row = sheet.createRow(index++);
						for (short i = 0; i < headersThree.length; i++) {
							HSSFCell cell = row.createCell((int) i);
							cell.setCellStyle(style);
							HSSFRichTextString text = new HSSFRichTextString(headersThree[i]);
							cell.setCellValue(text);
						}
						//����
						Iterator<HeightChartExportModel> itThree = listThree.iterator();
						while (itThree.hasNext()) {
							HSSFCell cell;
							row = sheet.createRow(index++);
							HeightChartExportModel t = itThree.next();
							cell = row.createCell((int) 0);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getType());
							cell = row.createCell((int) 1);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getSsv().toString());
							cell = row.createCell((int) 2);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getTest().toString());
							cell = row.createCell((int) 3);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getGrid().toString());
							cell = row.createCell((int) 4);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getKpi().toString());
							cell = row.createCell((int) 5);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getExtra().toString());
							cell = row.createCell((int) 6);
							cell.setCellStyle(style2);
							cell.setCellValue(t.getAll().toString());
						}
						index=index+2;
						*//**********************����ѭ�������***************************************//*
		
						
						*//**********************����ѭ����ʼ***************************************//*
						//ͼ
								if (!StringUtil.isBlank(chartForeName)) {
						        	BufferedImage bufferImg3 = null;
						        	ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
						        	File file=new File(parentPath+"\\"+chartForeName);
						        	if (file.exists()) {
						        		try {
						        			bufferImg3 = ImageIO.read(file);
						            		ImageIO.write(bufferImg3,getPictureType(chartForeName), byteArrayOut3);
						            		// ͼƬһ��������Ԫ��B2��  �м�����������
						            		HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
						            		// ����ͼƬ�м�����������  
						            		if (getPictureType(chartForeName).equals("png")) {
						            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
						            		}else {
						            			patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
						            		}
										} catch (Exception e) {
										}finally{
											if (file.exists()) {//ɾ���ļ�
												file.delete();
											}
										}
						        	}
						        	//������ע����
						        	 sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
						    		 HSSFRow rowRemark = sheet.createRow(index);
						    		 cell1 = rowRemark.createCell((int)7);
						    	     cell1.setCellStyle(stylebg);
						    	     cell1.setCellValue("��ע����:");
						        	index=index+16;
						        }
								// ������������
								 row = sheet.createRow(index++);
								for (short i = 0; i < headersFore.length; i++) {
									HSSFCell cell = row.createCell((int) i);
									cell.setCellStyle(style);
									HSSFRichTextString text = new HSSFRichTextString(headersFore[i]);
									cell.setCellValue(text);
								}
								//����
								Iterator<HeightChartExportModel> itFore = listFore.iterator();
								while (itFore.hasNext()) {
									HSSFCell cell;
									row = sheet.createRow(index++);
									HeightChartExportModel t = itFore.next();
									cell = row.createCell((int) 0);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getType());
									cell = row.createCell((int) 1);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getSsv().toString());
									cell = row.createCell((int) 2);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getTest().toString());
									cell = row.createCell((int) 3);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getGrid().toString());
									cell = row.createCell((int) 4);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getKpi().toString());
									cell = row.createCell((int) 5);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getExtra().toString());
									cell = row.createCell((int) 6);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getAll().toString());

								}
								index=index+2;
								*//**********************����ѭ�������***************************************//*
								*//**********************����ѭ����ʼ***************************************//*
								//ͼ
								if (!StringUtil.isBlank(chartFiveName)) {
									BufferedImage bufferImg3 = null;
									ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
									File file=new File(parentPath+"\\"+chartFiveName);
									if (file.exists()) {
										try {
											bufferImg3 = ImageIO.read(file);
											ImageIO.write(bufferImg3,getPictureType(chartFiveName), byteArrayOut3);
											// ͼƬһ��������Ԫ��B2��  �м�����������
											HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
											// ����ͼƬ�м�����������  
											if (getPictureType(chartFiveName).equals("png")) {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
											}else {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
											}
										} catch (Exception e) {
										}finally{
											if (file.exists()) {//ɾ���ļ�
												file.delete();
											}
										}
									}
									//������ע����
									sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
									HSSFRow rowRemark = sheet.createRow(index);
									cell1 = rowRemark.createCell((int)7);
									cell1.setCellStyle(stylebg);
									cell1.setCellValue("��ע����:");
									index=index+16;
								}
								// ������������
								row = sheet.createRow(index++);
								for (short i = 0; i < headersFive.length; i++) {
									HSSFCell cell = row.createCell((int) i);
									cell.setCellStyle(style);
									HSSFRichTextString text = new HSSFRichTextString(headersFive[i]);
									cell.setCellValue(text);
								}
								//����
								Iterator<HeightChartExportModel> iteratorFive = listFive.iterator();
								while (iteratorFive.hasNext()) {
									HSSFCell cell;
									row = sheet.createRow(index++);
									HeightChartExportModel t = iteratorFive.next();
									cell = row.createCell((int) 0);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getType());
									cell = row.createCell((int) 1);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getSsv().toString());
									cell = row.createCell((int) 2);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getGrid().toString());
									cell = row.createCell((int) 3);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getKpi().toString());
								}
								index=index+2;
								*//**********************����ѭ�������***************************************//*
								*//**********************����ѭ����ʼ***************************************//*
								//ͼ
								if (!StringUtil.isBlank(chartSixName)) {
									BufferedImage bufferImg3 = null;
									ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
									File file=new File(parentPath+"\\"+chartSixName);
									if (file.exists()) {
										try {
											bufferImg3 = ImageIO.read(file);
											ImageIO.write(bufferImg3,getPictureType(chartSixName), byteArrayOut3);
											// ͼƬһ��������Ԫ��B2��  �м�����������
											HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
											// ����ͼƬ�м�����������  
											if (getPictureType(chartSixName).equals("png")) {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
											}else {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
											}
										} catch (Exception e) {
										}finally{
											if (file.exists()) {//ɾ���ļ�
												file.delete();
											}
										}
									}
									//������ע����
									sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
									HSSFRow rowRemark = sheet.createRow(index);
									cell1 = rowRemark.createCell((int)7);
									cell1.setCellStyle(stylebg);
									cell1.setCellValue("��ע����:");
									index=index+16;
								}
								// ������������
								row = sheet.createRow(index++);
								for (short i = 0; i < headersSix.length; i++) {
									HSSFCell cell = row.createCell((int) i);
									cell.setCellStyle(style);
									HSSFRichTextString text = new HSSFRichTextString(headersSix[i]);
									cell.setCellValue(text);
								}
								//����
								Iterator<HeightChartExportModel> iteratorSix = listSix.iterator();
								while (iteratorSix.hasNext()) {
									HSSFCell cell;
									row = sheet.createRow(index++);
									HeightChartExportModel t = iteratorSix.next();
									cell = row.createCell((int) 0);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getType());
									cell = row.createCell((int) 1);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getSsv().toString());
									cell = row.createCell((int) 2);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getGrid().toString());
									cell = row.createCell((int) 3);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getKpi().toString());
								}
								index=index+2;
								*//**********************����ѭ�������***************************************//*
								*//**********************����ѭ����ʼ***************************************//*
								//ͼ
								if (!StringUtil.isBlank(chartSenvenName)) {
									BufferedImage bufferImg3 = null;
									ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
									File file=new File(parentPath+"\\"+chartSenvenName);
									if (file.exists()) {
										try {
											bufferImg3 = ImageIO.read(file);
											ImageIO.write(bufferImg3,getPictureType(chartSenvenName), byteArrayOut3);
											// ͼƬһ��������Ԫ��B2��  �м�����������
											HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
											// ����ͼƬ�м�����������  
											if (getPictureType(chartSenvenName).equals("png")) {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
											}else {
												patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
											}
										} catch (Exception e) {
										}finally{
											if (file.exists()) {//ɾ���ļ�
												file.delete();
											}
										}
									}
									//������ע����
									sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
									HSSFRow rowRemark = sheet.createRow(index);
									cell1 = rowRemark.createCell((int)7);
									cell1.setCellStyle(stylebg);
									cell1.setCellValue("��ע����:");
									index=index+16;
								}
								// ������������
								row = sheet.createRow(index++);
								for (short i = 0; i < headersSeven.length; i++) {
									HSSFCell cell = row.createCell((int) i);
									cell.setCellStyle(style);
									HSSFRichTextString text = new HSSFRichTextString(headersSeven[i]);
									cell.setCellValue(text);
								}
								//����
								Iterator<HeightChartExportModel> iteratorSenven = listSeven.iterator();
								while (iteratorSenven.hasNext()) {
									HSSFCell cell;
									row = sheet.createRow(index++);
									HeightChartExportModel t = iteratorSenven.next();
									cell = row.createCell((int) 0);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getType());
									cell = row.createCell((int) 1);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getSsv().toString());
									cell = row.createCell((int) 2);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getGrid().toString());
									cell = row.createCell((int) 3);
									cell.setCellStyle(style2);
									cell.setCellValue(t.getKpi().toString());
								}
								index=index+2;
								*//**********************����ѭ�������***************************************//*
		try {
			String  fileName=dateformat.format(new Date());
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment;filename=personAnalyse_"+fileName+".xls");
			out = response.getOutputStream();
			workbook.write(out);
		} catch (IOException e) {

		} finally {
			out.close();
		}
	}
	*//**
	 * �Ϻ�Ͷ��  ͼ����
	 *//*
	public void exportPersonCompmain(String[] headersOne, String[] headersTwo,
			String[] headersThree, String[] headersFore,String[] headersFive,
			String[] headersSix, String[] headersSeven,Collection<HeightChartExportModel> listOne, Collection<HeightChartExportModel> listTwo,
			Collection<HeightChartExportModel> listThree, Collection<HeightChartExportModel> listFore,Collection<HeightChartExportModel> listFive,
			Collection<HeightChartExportModel> listSix,Collection<HeightChartExportModel> listSeven, String chartOneName, String chartTwoName, String chartThreeName,
			String chartForeName,String chartFiveName, String chartSixName, String chartSenvenName, String title, HttpServletResponse response,String parentPath, String excelTitle) throws IOException {
		OutputStream out = response.getOutputStream();
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet(title);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
		sheet.setDefaultColumnWidth((int) 12);
		// ����һ����ʽ ����ר��
		HSSFCellStyle style = workbook.createCellStyle();
		// ������Щ��ʽ
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ����һ������
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ������Ӧ�õ���ǰ����ʽ
		style.setFont(font);
		
		// ���ɲ�������һ����ʽ
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// ������һ������
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		style2.setFont(font2);
		
		// ���ɲ�������һ����ʽ ����
		HSSFDataFormat format = workbook.createDataFormat();
		HSSFCellStyle styleDate = workbook.createCellStyle();
		styleDate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleDate.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleDate.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleDate.setVerticalAlignment(HSSFCellStyle.ALIGN_LEFT);
		styleDate.setDataFormat(format.getFormat("yyyy-MM-dd"));
		// ������һ������
		HSSFFont fontDate = workbook.createFont();
		fontDate.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		styleDate.setFont(fontDate);
		
		
		// ��ע����ɫ
		HSSFCellStyle stylebg = workbook.createCellStyle();
		// ������Щ��ʽ
		stylebg.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
		stylebg.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		stylebg.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderRight(HSSFCellStyle.BORDER_THIN);
		stylebg.setBorderTop(HSSFCellStyle.BORDER_THIN);
		stylebg.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		stylebg.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		
		
		// ����һ����ͼ�Ķ���������
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		*//**********************����ѭ����ʼ***************************************//*
		int index=0;
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));//���򣺺ϲ���0�еĵ�0�е���9��      ��ʼ����ֹ�� ��ʼ�� ��ֹ��
		HSSFRow row1 = sheet.createRow(index++);
		row1.setHeightInPoints(40);  
		HSSFCell cell1 = row1.createCell((int)0);
		cell1.setCellStyle(style);
		cell1.setCellValue(excelTitle);
		//ͼ
		if (!StringUtil.isBlank(chartOneName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartOneName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartOneName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);//�� ��
					// ����ͼƬ�м�����������  
					if (getPictureType(chartOneName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		
		HSSFRow row ;
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersOne.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersOne[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> it = listOne.iterator();
		while (it.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = it.next();
			
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
			cell = row.createCell((int) 2);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getTest().toString());
		}
		index=index+2;
		*//**********************����ѭ������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartTwoName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartTwoName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartTwoName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartTwoName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersTwo.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersTwo[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> itTwo = listTwo.iterator();
		while (itTwo.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = itTwo.next();
			
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
			cell = row.createCell((int) 2);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getTest().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartThreeName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartThreeName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartThreeName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartThreeName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersThree.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersThree[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> itThree = listThree.iterator();
		while (itThree.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = itThree.next();
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartForeName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartForeName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartForeName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartForeName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersFore.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersFore[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> itFore = listFore.iterator();
		while (itFore.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = itFore.next();
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartFiveName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartFiveName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartFiveName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartFiveName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersFive.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersFive[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> iteratorFive = listFive.iterator();
		while (iteratorFive.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = iteratorFive.next();
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartSixName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartSixName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartSixName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartSixName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersSix.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersSix[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> iteratorSix = listSix.iterator();
		while (iteratorSix.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = iteratorSix.next();
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		*//**********************����ѭ����ʼ***************************************//*
		//ͼ
		if (!StringUtil.isBlank(chartSenvenName)) {
			BufferedImage bufferImg3 = null;
			ByteArrayOutputStream byteArrayOut3 = new ByteArrayOutputStream();
			File file=new File(parentPath+"\\"+chartSenvenName);
			if (file.exists()) {
				try {
					bufferImg3 = ImageIO.read(file);
					ImageIO.write(bufferImg3,getPictureType(chartSenvenName), byteArrayOut3);
					// ͼƬһ��������Ԫ��B2��  �м�����������
					HSSFClientAnchor anchor3 = new HSSFClientAnchor(0, 0, 0, 0,(short) 0,index, (short) 7,index+16);
					// ����ͼƬ�м�����������  
					if (getPictureType(chartSenvenName).equals("png")) {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));//PICTURE_TYPE_JPEG
					}else {
						patriarch.createPicture(anchor3, workbook.addPicture(byteArrayOut3.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));//PICTURE_TYPE_JPEG
					}
				} catch (Exception e) {
				}finally{
					if (file.exists()) {//ɾ���ļ�
						file.delete();
					}
				}
			}
			//������ע����
			sheet.addMergedRegion(new CellRangeAddress(index,index+15,7,11));//���򣺺ϲ���0�еĵ�0�е���9��
			HSSFRow rowRemark = sheet.createRow(index);
			cell1 = rowRemark.createCell((int)7);
			cell1.setCellStyle(stylebg);
			cell1.setCellValue("��ע����:");
			index=index+16;
		}
		// ������������
		row = sheet.createRow(index++);
		for (short i = 0; i < headersSeven.length; i++) {
			HSSFCell cell = row.createCell((int) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headersSeven[i]);
			cell.setCellValue(text);
		}
		//����
		Iterator<HeightChartExportModel> iteratorSenven = listSeven.iterator();
		while (iteratorSenven.hasNext()) {
			HSSFCell cell;
			row = sheet.createRow(index++);
			HeightChartExportModel t = iteratorSenven.next();
			cell = row.createCell((int) 0);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getType());
			cell = row.createCell((int) 1);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getSsv().toString());
			cell = row.createCell((int) 2);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getTest().toString());
			cell = row.createCell((int) 3);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getGrid().toString());
			cell = row.createCell((int) 4);
			cell.setCellStyle(style2);
			cell.setCellValue(t.getKpi().toString());
		}
		index=index+2;
		*//**********************����ѭ�������***************************************//*
		try {
			String  fileName=dateformat.format(new Date());
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment;filename=complainAnalyse_"+fileName+".xls");
			out = response.getOutputStream();
			workbook.write(out);
		} catch (IOException e) {
			
		} finally {
			out.close();
		}
	}
	
	*//**
	 * ��ȡͼƬ�ĺ�׺����
	 *//*
	public String getPictureType(String fileName){
		String proType=fileName.substring(fileName.lastIndexOf(".")+1);
		return proType;
	}
	
	*//**
	 * ssv��Ա����
	 * @throws Exception 
	 *//*
	public HeightchartModel ssvPersonAnalyseNum(String pid, String begindate,String enddate, List<String> listPersonList, HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("SSVͨ��#", "column");
		SeriesModel seriesModelTwo=new SeriesModel("SSV#δͨ��", "column");
		SeriesModel seriesModelThree=new SeriesModel("SSV%", "spline");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate)) {*//**���û�д���ʱ�� ��ô����Ĭ�ϵ�������   ����Ļ����ǲ��ô��������*//*
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}
		*//***ǰ��������ʾʱ���*//*
		highChartModel.setBeginDate(begindate);
		highChartModel.setEndDate(enddate);
		int num=listPersonList.size();
	       if (num>0) {
				for (int i = 0; i < num; i++) {*//**��ʼѭ������*//*
					*//**ÿ���˵��������*//*
					//ͨ��1
					String personName=listPersonList.get(i);
					double isPassOne=getRowsCount("select 1 from dyjl where projId='"+pid+"' and deleted='0' and (person1=person2 or (person2 is null or length(person2)<=0)) and  testDate between  '"+begindate+"' and '"+enddate+"' and person1='"+personName+"' and test ='1' ");
					//ͨ��0.5
					double isPassHalf=getRowsCount("select 1 from dyjl where projId='"+pid+"' and deleted='0' and person1 !=person2  and person2 is not null and length(person2)>0 and (person2 ='"+personName+"' or person1='"+personName+"') and  testDate between  '"+begindate+"' and '"+enddate+"' and test ='1' ")/2.0;
					//ͨ��
					double passFinally=isPassOne+isPassHalf;
				
					//��ͨ��1
					double notPassOne=getRowsCount("select 1 from dyjl where projId='"+pid+"' and deleted='0' and (person1=person2 or (person2 is null or length(person2)<=0)) and  testDate between  '"+begindate+"' and '"+enddate+"' and person1='"+personName+"' and test ='0' ");
					//ͨ��0.5
					double notPassHalf=getRowsCount("select 1 from dyjl where projId='"+pid+"' and deleted='0' and person1 !=person2 and person2 is not  null and length(person2) >0 and (person2 ='"+personName+"' or person1='"+personName+"') and  testDate between  '"+begindate+"' and '"+enddate+"' and test ='0' ")/2.0;
					//ͨ��
					double notPassFinally=notPassOne+notPassHalf;
					//ͨ����
					double persentNum=0;
					double all=passFinally+notPassFinally;
					if (all != 0) {
						persentNum=(double)passFinally/(double)(passFinally+notPassFinally);
					}
					seriesModelOne.getData().add(passFinally);
					seriesModelTwo.getData().add(notPassFinally);
					seriesModelThree.getData().add(persentNum);
				}
		  }
	       highChartModel.setCategories(listPersonList);
	       highChartModel.getSeries().add(seriesModelOne);
	       highChartModel.getSeries().add(seriesModelTwo);
	       highChartModel.getSeries().add(seriesModelThree);
	       *//***ÿ�������������뻺��*//*
	       if (session.getAttribute(ApplicationConst.ssvPersonAnalyse) !=null) {
			session.removeAttribute(ApplicationConst.ssvPersonAnalyse);
	       }
	       session.setAttribute(ApplicationConst.ssvPersonAnalyse, highChartModel);
		return highChartModel;
	}
	*//**
	 * grid��Ա����
	 *//*
	public HeightchartModel gridPersonAnalyseNum(String pid, String begindate,String enddate, List<String> listPersonList, HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		SeriesModel seriesModelOne=new SeriesModel("Grid���#", "column");
		SeriesModel seriesModelTwo=new SeriesModel("Grid#δ���", "column");
		SeriesModel seriesModelThree=new SeriesModel("Grid%", "spline");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate)) {*//**���û�д���ʱ�� ��ô����Ĭ�ϵ�������   ����Ļ����ǲ��ô��������*//*
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}
		*//***ǰ��������ʾʱ���*//*
		highChartModel.setBeginDate(begindate);
		highChartModel.setEndDate(enddate);
		int num=listPersonList.size();
		if (num>0) {
			for (int i = 0; i < num; i++) {*//**��ʼѭ������*//*
				String personName=listPersonList.get(i);
				//��
				double isPass=getRowsCount(" select 1  from grid_net_problem where projId='"+pid+"' and deleted='0' and (proOkTime is null or length(proOkTime)<=0 or (proOkTime is not null and proOkTime between '"+begindate+"' and '"+enddate+"')) and (proTime between '"+begindate+"' and '"+enddate+"') and proPeoson='"+personName+"' and proOr='1' ");
				//��
				double notPass=getRowsCount("select 1  from grid_net_problem where projId='"+pid+"' and deleted='0' and (proOkTime is null or length(proOkTime)<=0 or (proOkTime is not null and proOkTime between '"+begindate+"' and '"+enddate+"')) and (proTime between '"+begindate+"' and '"+enddate+"') and proPeoson='"+personName+"' and proOr='0' ");
				//ͨ����
				double persentNum=0;
				double all=isPass+notPass;
				if (all != 0) {
					persentNum=(double)isPass/(double)(all);
				}
				seriesModelOne.getData().add(isPass);
				seriesModelTwo.getData().add(notPass);
				seriesModelThree.getData().add(persentNum);
			}
		}
		highChartModel.setCategories(listPersonList);
		highChartModel.getSeries().add(seriesModelOne);
		highChartModel.getSeries().add(seriesModelTwo);
		highChartModel.getSeries().add(seriesModelThree);
		if (session.getAttribute(ApplicationConst.gridPersonAnalyse) !=null) {
			session.removeAttribute(ApplicationConst.gridPersonAnalyse);
		}
		session.setAttribute(ApplicationConst.gridPersonAnalyse, highChartModel);
		return highChartModel;
	}
	*//**
	 * kpi��Ա����
	 *//*
	public HeightchartModel kpiPersonAnalyseNum(String pid, String begindate,String enddate, HttpSession session) throws Exception {
		HeightchartModel highChartModel=new HeightchartModel();
		List<String> listCategories=new ArrayList<String>();
		SeriesModel seriesModelOne=new SeriesModel("KPI#", "column");
		SeriesModel seriesModelTwo=new SeriesModel("KPI avg(min)", "column");
		SeriesModel seriesModelThree=new SeriesModel("KPI%", "spline");
		
		if (StringUtil.isBlank(begindate) && StringUtil.isBlank(enddate)) {*//**���û�д���ʱ�� ��ô����Ĭ�ϵ�������   ����Ļ����ǲ��ô��������*//*
			begindate=MyUtil.getDateBeforSevenDay(-7);
			enddate=MyUtil.getDateBeforSevenDay(-1);
		}
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append(" select m.isperoson,m.countAllNum,m.avgNum,n.countOkNum from (select d.isperoson,count(*) as countAllNum,avg(d.istime) as avgNum   from (select  isdate,isperoson,isok,istime from (select switchdate isdate,engineer isperoson,solved isok,swminute istime from kpi_net_switch where projId='"+pid+"' and deleted='0' and switchdate between '"+begindate+"' and '"+enddate+"' union all select rrcswitchdate isdate,rrcengineer isperson,oknok isok,rrcswminute istime from kpi_net_rrc where projId='"+pid+"' and deleted='0' and rrcswitchdate between '"+begindate+"' and '"+enddate+"' union all select rabswitchdate isdate,rabengineer isperson,raboknok isok,rabswminute istime from kpi_net_rab where projId='"+pid+"' and deleted='0' and rabswitchdate between '"+begindate+"' and '"+enddate+"' union all select unswitchdate isdate,unengineer isperson,unoknok isok,unswminute istime from kpi_net_unline where projId='"+pid+"' and deleted='0' and unswitchdate between '"+begindate+"' and '"+enddate+"') a) d group by d.isperoson) m left join (select d.isperoson,count(d.isok) as countOkNum  from (select  isdate,isperoson,isok,istime from (select switchdate isdate,engineer isperoson,solved isok,swminute istime from kpi_net_switch where projId='"+pid+"' and deleted='0' and solved='1'  and switchdate between '"+begindate+"' and '"+enddate+"' union all select rrcswitchdate isdate,rrcengineer isperson,oknok isok,rrcswminute istime from kpi_net_rrc where projId='"+pid+"' and deleted='0' and oknok='1' and rrcswitchdate between '"+begindate+"' and '"+enddate+"' union all select rabswitchdate isdate,rabengineer isperson,raboknok isok,rabswminute istime from kpi_net_rab where projId='"+pid+"' and deleted='0' and raboknok='1' and rabswitchdate between '"+begindate+"' and '"+enddate+"' union all select unswitchdate isdate,unengineer isperson,unoknok isok,unswminute istime from kpi_net_unline where projId='"+pid+"' and unoknok='1' and deleted='0' and unswitchdate between '"+begindate+"' and '"+enddate+"') a ) d group by d.isperoson) n on m.isperoson=n.isperoson ");
			ps = ct.prepareStatement(sql.toString());
			rs = ps.executeQuery();
			while(rs.next()){
				//��Ա����
				String name=rs.getString("isperoson");
				//������ܸ���
				double countAllNum=rs.getDouble("countAllNum");
				//�����ƽ��ʱ��
				double avgNum=rs.getDouble("avgNum");
				//����ĸ���
				double countOkNum=rs.getDouble("countOkNum");
				//�����
				double okPercent=0;
				if (countAllNum != 0) {
					okPercent=countOkNum/countAllNum;
				}
				listCategories.add(name);
				seriesModelOne.getData().add(countAllNum);
				seriesModelTwo.getData().add(avgNum);
				seriesModelThree.getData().add(okPercent);
			}
		} catch (Exception e) {
		}finally{
			super.close(ps, rs);
		}
		
		highChartModel.setCategories(listCategories);
		highChartModel.getSeries().add(seriesModelOne);
		highChartModel.getSeries().add(seriesModelTwo);
		highChartModel.getSeries().add(seriesModelThree);
		
		if (session.getAttribute(ApplicationConst.kpiPersonAnalyse) !=null) {
			session.removeAttribute(ApplicationConst.kpiPersonAnalyse);
		}
		session.setAttribute(ApplicationConst.kpiPersonAnalyse, highChartModel);
		return highChartModel;
	}
	
}
*/