package com.boot.kaizen.backUpFile;
/*package com.boot.security.server.backUpFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.byb.base.action.BaseAction;
import com.byb.core.jsonutil.ApplicationConst;
import com.byb.core.jsonutil.JsonWriteUtil;
import com.byb.core.jsonutil.MyUtil;
import com.byb.core.project.ProjectService;
import com.byb.core.project.pojo.Project;
import com.byb.core.task.heighchart.pojo.HeightChartExportModel;
import com.byb.core.task.heighchart.pojo.HeightchartModel;
import com.byb.core.task.heighchart.pojo.SeriesModel;
import com.byb.util.tools.StringUtil;
import com.fr.third.org.hsqldb.Session;
*//**
 *heighchart  ͼ������ͳ��
 *//*
public class HeighchartTaskAction extends BaseAction{
	private static final long serialVersionUID = 1562470586453388912L;
	private HeighchartTaskService heighchartTaskService=new HeighchartTaskService();
	
	*//**
	*
	*
	*
	*
	*
	 *  �Ϻ�Ͷ�߹�����ͳ��  ���Ϻ�Ͷ�ߣ�
		ͳ��ͼ��1���գ�����7��Ϊ��ʾ����ͳ��
		ÿ��Ͷ�������ԡ��յ����ڡ��н���ͳ�ƣ�
		ÿ�ջص������ԡ��ص����ڡ��н���ͳ�ƣ�
	 *//*
	public void complainStatNumByDay() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.ComplainStatNumByDay(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**
	 *  ����������ͳ��  ���Ϻ�Ͷ�ߣ�
		ͳ��ͼ��1���գ�����7��Ϊ��ʾ����ͳ��
		ÿ�ղ��Լƻ������ԡ��������ڡ�+�����Ը��ڡ��н���ͳ�ƣ�
		ÿ�ղ�����������ԡ���ɲ������ڡ��н���ͳ�ƣ�
	 *//*
	public void complainStatNumTestDate() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumTestDate(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**
	 *  ͳ��ͼ��1���£�������Ϊ��ʾ����ͳ��  ���Ϻ�Ͷ�ߣ�
		����Ͷ�������ԡ��յ����ڡ��н��л���ͳ�ơ�Y/N����Y��������
	 *//*
	public void complainStatNumPromoto() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumPromoto(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**
	 *  ͳ��ͼ��1���£�����7��ּ����ⶨλͳ��  ���Ϻ�Ͷ�ߣ�
	 *//*
	public void complainStatNumPosition() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumPosition(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**
	 *  ͳ��ͼ��1���£�����7��������ⶨλͳ��  ���Ϻ�Ͷ�ߣ�
	 *//*
	public void complainStatNumTestPosition() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumTestPosition(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**��7��ÿ�չ�������ʱ����ƽ�����գ���ͳ��  *//*
	public void complainStatNumBackStepDateLast() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumBackStepDateLast(getSessionProjId(),type,begindate,enddate,getSession()));
	}	
	*//**ÿ��ÿ����������ʱ����ƽ����ͳ�� ����  *//*
	public void complainStatNumAvgTimeDay() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.complainStatNumAvgTimeDay(getSessionProjId(),type,begindate,enddate,getProjectPersonKpiList(),getSession()));
	}	
	
	
	*//**
	 * ����Ŀ�µ�kpi��Ա��id��ѯ
	 * ���˵���ͬ��id
	 *//*
	public List<String> getProjectPersonKpiList(){
		ProjectService projectService=new ProjectService();
		//��Ŀ�´����Ա���ֶ�grid  kpi ssv xmjl gridsp kpisp,ssvsp
		Project project=(Project) projectService.getObjById(Project.class, getSessionProjId());
		List<String> list=new ArrayList<String>();
		
		if (!StringUtil.isBlank(project.getKpiSp())) {
			String [] str=project.getKpiSp().split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		if (!StringUtil.isBlank(project.getKpi())) {
			String [] str=project.getKpi().split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		if (!StringUtil.isBlank(project.getXmjl())) {
			String [] str=project.getXmjl().split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		if (!StringUtil.isBlank(project.getXmzl())) {
			String [] str=project.getXmzl().split(",");
			for (int i = 0; i < str.length; i++) {
				list.add(str[i]);
			}
		}
		return queryListNoSame(list);
	}
	
	*//**
	 * ����list����  ����û����ͬ��¼��list
	 *//*
	public List<String> queryListNoSame(List<String> list){
		List<String> list2=new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (!list2.contains(list.get(i))) {
				list2.add(list.get(i));
			}
		}
		return list2;
	}
	
	
	*//*******************************************************************
	 * 					��Ա��������
	 * *****************************************************************
	 *//*
	
	*//**
	 * ��ת����Ա������ҳ��
	 * Ĭ�ϲ�ѯ����ǰ�����ڵ����е��� ����session
	 *//*
	public String goPersonAnalysePage() throws Exception{
		return "goPersonAnalysePage";
	}
	
	*//**
	 * ѡ��ʱ����ÿ����������personAnalyseNum
	 *//*
	@SuppressWarnings("unchecked")
	public void personAnalyseNum() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		List<String> listDates=new ArrayList<String>();
		heighchartTaskService.goPersonAnalysePageThreeMonth(getSession(),getSessionProjId(),begindate,enddate);
		listDates=(List<String>) getSession().getAttribute(ApplicationConst.highchartPersonList);
		JsonWriteUtil.writeJson(heighchartTaskService.personAnalyseNum(getSessionProjId(),begindate,enddate,listDates,getSession()));
	}	
	
	*//**
	 * ѡ��ʱ����ÿ������
	 *//*
	public void personAnalyseNumByDay() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.personAnalyseNumByDay(getSessionProjId(),begindate,enddate,getSession()));
	}	
	*//**
	 * ʱ��θ���Ŀ������  �˴�
	 *//*
	public void personAnalyseNumByPro() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.personAnalyseNumByPro(getSessionProjId(),begindate,enddate,getSession()));
	}	
	*//**
	 * ʱ��θ���Ŀ������  ����
	 *//*
	public void personAnalyseNumByPersonDay() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.personAnalyseNumByPersonDay(getSessionProjId(),begindate,enddate,getSession()));
	}	
	
	*//**
	 * ssv��Ա����
	 *//*
	public void ssvPersonAnalyseNum() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		List<String> listDates=heighchartTaskService.goSsvPersonAnalyseNum(getSession(),getSessionProjId(),begindate,enddate);
		JsonWriteUtil.writeJson(heighchartTaskService.ssvPersonAnalyseNum(getSessionProjId(),begindate,enddate,listDates,getSession()));
	}	
	*//**
	 * grid��Ա����
	 *//*
	public void gridPersonAnalyseNum() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		List<String> listDates=heighchartTaskService.goGridPersonAnalyseNum(getSession(),getSessionProjId(),begindate,enddate);
		JsonWriteUtil.writeJson(heighchartTaskService.gridPersonAnalyseNum(getSessionProjId(),begindate,enddate,listDates,getSession()));
	}	
	*//**
	 * kpi��Ա����
	 *//*
	public void kpiPersonAnalyseNum() throws Exception{
		getResponse().setContentType("text/json"); 
		getResponse().setCharacterEncoding("UTF-8"); //���gbk��Ŀ  json���������
		JsonWriteUtil.writeJson(heighchartTaskService.kpiPersonAnalyseNum(getSessionProjId(),begindate,enddate,getSession()));
	}	
	
	
	*//**
	 * �Ϻ�Ͷ�ߵ���
	 *//*
	public void exportComplainAnalyse() throws Exception{
		String excelTitle="KEZEN online ����("+begindateM+"/"+enddateM+")";  //����
		*//**��ȡ��ͼ���session���ݼ���*//*
		HeightchartModel complainStatNumByDayModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumByDay);
		HeightchartModel complainStatNumTestDateModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumTestDate);
		HeightchartModel complainStatNumPromotoModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumPromoto);
		HeightchartModel complainStatNumPositionModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumPosition);
		
		HeightchartModel complainStatNumTestPositionModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumTestPosition);
		HeightchartModel complainStatNumBackStepDateLastModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumBackStepDateLast);
		HeightchartModel complainStatNumAvgTimeDayModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.complainStatNumAvgTimeDay);
		
		
		//׼������
		Collection<HeightChartExportModel> listOne=listToCollectionComplainA(complainStatNumByDayModel);// �Ϻ�Ͷ�߹�����ͳ�� 
		Collection<HeightChartExportModel> listTwo=listToCollectionComplainA(complainStatNumTestDateModel);//����������ͳ��  2����
		Collection<HeightChartExportModel> listThree=listToCollectionB(complainStatNumPromotoModel);//����Ͷ�����ͳ�� 
		Collection<HeightChartExportModel> listFore=listToCollectionB(complainStatNumPositionModel);//��7��ּ����ⶨλͳ��   
		
		Collection<HeightChartExportModel> listFive=listToCollectionB(complainStatNumTestPositionModel);//��7��������ⶨλͳ��
		Collection<HeightChartExportModel> listSix=listToCollectionB(complainStatNumBackStepDateLastModel);//��7��ÿ�չ�������ʱ��   1����
		Collection<HeightChartExportModel> listSeven=listToCollectionComplainB(complainStatNumAvgTimeDayModel);//ÿ��ÿ����������ʱ����ƽ����ͳ��   4����
		
		//׼������
		String[] headersOne ={"����", "�յ���","�ص���"};
		String[] headersTwo =   {"����","#CQT down","#CQT planned"};
		String[] headersThree = {"����", "����"};
		String[] headersFore = {"�ּ����ⶨλ","����"};
		String[] headersFive ={"�������ⶨλ", "����"};
		String[] headersSix ={"����", "����"};
		String[] headersSeven ={"����", "�ּ�","ԤԼ","����","�ص�"};
		//׼��ͼƬ����  ͼƬ�ڵ���excel��ϵ�ʱ�� ����ɾ����  ������������ͼƬ
		String path = getSession().getServletContext().getRealPath("/attached/");
		
		String chartOneName=MyUtil.stringToPicture(path, complainStatNumByDayM);
		String chartTwoName=MyUtil.stringToPicture(path, complainStatNumTestDateM);
		String chartThreeName=MyUtil.stringToPicture(path, complainStatNumPromotoM);
		String chartForeName=MyUtil.stringToPicture(path, complainStatNumPositionM);
		String chartFiveName=MyUtil.stringToPicture(path, complainStatNumTestPositionM);
		String chartSixName=MyUtil.stringToPicture(path, complainStatNumBackStepDateLastM);
		String chartSenvenName=MyUtil.stringToPicture(path, complainStatNumAvgTimeDayM);
		
		heighchartTaskService.exportPersonCompmain(headersOne,headersTwo,headersThree,headersFore,headersFive,headersSix,headersSeven,listOne,listTwo,listThree,listFore,listFive,listSix,listSeven,chartOneName,chartTwoName,chartThreeName,chartForeName,chartFiveName,chartSixName,chartSenvenName,"KEZEN online ����-�Ϻ���ͨͶ��",getResponse(),path,excelTitle);
//		heighchartTaskService.exportPersonAnalyse(headersOne,headersTwo,headersThree,headersFore,headersFive,headersSix,headersSeven,listOne,listTwo,listThree,listFore,listFive,listSix,listSeven,chartOneName,chartTwoName,chartThreeName,chartForeName,chartFiveName,chartSixName,chartSenvenName,"KEZEN online ����-�Ϻ���ͨͶ��",getResponse(),path,excelTitle);
	
	}
	*//**
	 * ��Ա��������
	 *//*
	public void exportPersonAnalyse() throws Exception{
		String excelTitle="��Ա���ݷ���("+begindateM+"/"+enddateM+")";  //����
		*//**��ȡ��ͼ���session���ݼ���*//*
		HeightchartModel personAnalyseNumModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.personAnalyseNum);
		HeightchartModel personAnalyseNumByDayModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.personAnalyseNumByDay);
		HeightchartModel personAnalyseNumByPersonDayModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.personAnalyseNumByPersonDay);
		HeightchartModel personAnalyseNumByProModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.personAnalyseNumByPro);
		
		HeightchartModel ssvPersonAnalyseModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.ssvPersonAnalyse);
		HeightchartModel gridPersonAnalyseModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.gridPersonAnalyse);
		HeightchartModel kpiPersonAnalyseModel=(HeightchartModel) getSession().getAttribute(ApplicationConst.kpiPersonAnalyse);
		
		
		//׼������
		Collection<HeightChartExportModel> listOne=listToCollectionB(personAnalyseNumByProModel);// �˴� 
		Collection<HeightChartExportModel> listTwo=listToCollectionB(personAnalyseNumByPersonDayModel);//����
		Collection<HeightChartExportModel> listThree=listToCollectionA(personAnalyseNumByDayModel);//ÿ�������  
		Collection<HeightChartExportModel> listFore=listToCollectionA(personAnalyseNumModel);//ÿ����������

		Collection<HeightChartExportModel> listFive=listToCollectionC(ssvPersonAnalyseModel);//ssv��Ա����
		Collection<HeightChartExportModel> listSix=listToCollectionC(gridPersonAnalyseModel);//grid��Ա����
		Collection<HeightChartExportModel> listSeven=listToCollectionC(kpiPersonAnalyseModel);//kpi��Ա����
		
		//׼������
		String[] headersOne ={"����", "����"};
		String[] headersTwo =   {"����","����"};
		String[] headersThree = {"����", "SSV","Test","Grid","Kpi","Extra","All"};
		String[] headersFore = {"����", "SSV","Test","Grid","Kpi","Extra","All"};
		String[] headersFive ={"����", "SSVͨ��#","SSV#δͨ��","SSV%"};
		String[] headersSix ={"����", "Grid���#","Grid#δ���","Grid%"};
		String[] headersSeven ={"����", "KPI#","KPI avg(min)","KPI%"};
		//׼��ͼƬ����  ͼƬ�ڵ���excel��ϵ�ʱ�� ����ɾ����  ������������ͼƬ
		String path = getSession().getServletContext().getRealPath("/attached/");
		String chartOneName=MyUtil.stringToPicture(path, personAnalyseNumByProM);
		String chartTwoName=MyUtil.stringToPicture(path, personAnalyseNumByPersonDayM);
		String chartThreeName=MyUtil.stringToPicture(path, personAnalyseNumByDayM);
		String chartForeName=MyUtil.stringToPicture(path, personAnalyseNumM);
		
		
		String chartFiveName=MyUtil.stringToPicture(path, ssvPersonAnalyseNumM);
		String chartSixName=MyUtil.stringToPicture(path, gridPersonAnalyseNumM);
		String chartSenvenName=MyUtil.stringToPicture(path, kpiPersonAnalyseNumM);
		
		heighchartTaskService.exportPersonAnalyse(headersOne,headersTwo,headersThree,headersFore,headersFive,headersSix,headersSeven,listOne,listTwo,listThree,listFore,listFive,listSix,listSeven,chartOneName,chartTwoName,chartThreeName,chartForeName,chartFiveName,chartSixName,chartSenvenName,"��Ա����",getResponse(),path,excelTitle);
	}
	*//***
	 * ��HeightchartModelת��Ϊcollection
	 * ��Ա����ǰ����ʹ��
	 *//*
	public Collection<HeightChartExportModel>  listToCollectionB(HeightchartModel heightchartModel){
		Collection<HeightChartExportModel> collection=new ArrayList<HeightChartExportModel>();
		List<String> categories =heightchartModel.getCategories();
		List<Object> lisDates=heightchartModel.getSeries().get(0).getData();
		for (int i = 0; i < categories.size(); i++) {
			HeightChartExportModel heightChartExportModel=new HeightChartExportModel();
			String type=categories.get(i);
			heightChartExportModel.setType(type);
			heightChartExportModel.setSsv(lisDates.get(i));
			collection.add(heightChartExportModel);
		}
		return collection;
	}
	*//***
	 * ��HeightchartModelת��Ϊcollection   
	 * �Ϻ�Ͷ��  
	 *//*
	public Collection<HeightChartExportModel>  listToCollectionComplainA(HeightchartModel heightchartModel){
		Collection<HeightChartExportModel> collection=new ArrayList<HeightChartExportModel>();
		Collection<HeightChartExportModel> collection1=new ArrayList<HeightChartExportModel>();
		List<String> categories =heightchartModel.getCategories();//�ܶ�
		if (categories.size()>0) {
			List<SeriesModel> lisSeriesModels=heightchartModel.getSeries();//2��
			for (int i = 0; i < categories.size(); i++) {
				HeightChartExportModel heightChartExportModel=new HeightChartExportModel();
				String type=categories.get(i);
				heightChartExportModel.setType(type);
				collection.add(heightChartExportModel);
			}
			Iterator<HeightChartExportModel> it = collection.iterator();
			int i=0;
			while (it.hasNext()) {
				HeightChartExportModel h=it.next();
				for (int j = 0; j < 6; j++) {
					if (j==0) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setSsv(list.get(i));
					}
					if (j==1) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setTest(list.get(i));
					}
				}
				collection1.add(h);
				i++;
			}
		}
		return collection1;
	}
	*//***
	 * ��HeightchartModelת��Ϊcollection
	 * �Ϻ�Ͷ��  
	 *//*
	public Collection<HeightChartExportModel>  listToCollectionComplainB(HeightchartModel heightchartModel){
		Collection<HeightChartExportModel> collection=new ArrayList<HeightChartExportModel>();
		Collection<HeightChartExportModel> collection1=new ArrayList<HeightChartExportModel>();
		List<String> categories =heightchartModel.getCategories();//�ܶ�
		if (categories.size()>0) {
			List<SeriesModel> lisSeriesModels=heightchartModel.getSeries();//4��
			for (int i = 0; i < categories.size(); i++) {
				HeightChartExportModel heightChartExportModel=new HeightChartExportModel();
				String type=categories.get(i);
				heightChartExportModel.setType(type);
				collection.add(heightChartExportModel);
			}
			Iterator<HeightChartExportModel> it = collection.iterator();
			int i=0;
			while (it.hasNext()) {
				HeightChartExportModel h=it.next();
				for (int j = 0; j < 6; j++) {
					if (j==0) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setSsv(list.get(i));
					}
					if (j==1) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setTest(list.get(i));
					}
					if (j==2) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setGrid(list.get(i));
					}
					if (j==3) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setKpi(list.get(i));
					}
				}
				collection1.add(h);
				i++;
			}
		}
		return collection1;
	}
	*//***
	 * ��HeightchartModelת��Ϊcollection
	 * ��Ա�������ʹ��
	 *//*
	public Collection<HeightChartExportModel>  listToCollectionA(HeightchartModel heightchartModel){
		Collection<HeightChartExportModel> collection=new ArrayList<HeightChartExportModel>();
		Collection<HeightChartExportModel> collection1=new ArrayList<HeightChartExportModel>();
		List<String> categories =heightchartModel.getCategories();//�ܶ�
		if (categories.size()>0) {
			List<SeriesModel> lisSeriesModels=heightchartModel.getSeries();//6��
			for (int i = 0; i < categories.size(); i++) {
				HeightChartExportModel heightChartExportModel=new HeightChartExportModel();
				String type=categories.get(i);
				heightChartExportModel.setType(type);
				collection.add(heightChartExportModel);
			}
			Iterator<HeightChartExportModel> it = collection.iterator();
			int i=0;
			while (it.hasNext()) {
				HeightChartExportModel h=it.next();
				for (int j = 0; j < 6; j++) {
					if (j==0) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setSsv(list.get(i));
					}
					if (j==1) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setTest(list.get(i));
					}
					if (j==2) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setGrid(list.get(i));
					}
					if (j==3) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setKpi(list.get(i));
					}
					if (j==4) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setExtra(list.get(i));
					}
					if (j==5) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setAll(list.get(i));
					}
				}
				collection1.add(h);
				i++;
			}
		}
		return collection1;
	}
	
	*//***
	 * 
	 * gird ssv kpi ��Ա�������ʹ��
	 *//*
	public Collection<HeightChartExportModel>  listToCollectionC(HeightchartModel heightchartModel){
		Collection<HeightChartExportModel> collection=new ArrayList<HeightChartExportModel>();
		Collection<HeightChartExportModel> collection1=new ArrayList<HeightChartExportModel>();
		List<String> categories =heightchartModel.getCategories();//�ܶ�
		if (categories.size()>0) {
			List<SeriesModel> lisSeriesModels=heightchartModel.getSeries();//6��
			for (int i = 0; i < categories.size(); i++) {
				HeightChartExportModel heightChartExportModel=new HeightChartExportModel();
				String type=categories.get(i);
				heightChartExportModel.setType(type);
				collection.add(heightChartExportModel);
			}
			Iterator<HeightChartExportModel> it = collection.iterator();
			int i=0;
			while (it.hasNext()) {
				HeightChartExportModel h=it.next();
				for (int j = 0; j < 3; j++) {
					if (j==0) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setSsv(list.get(i));
					}
					if (j==1) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setGrid(list.get(i));
					}
					if (j==2) {
						List<Object> list=lisSeriesModels.get(j).getData();
						h.setKpi(list.get(i));
					}
				}
				collection1.add(h);
				i++;
			}
		}
		return collection1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	*//**��Ա����*//*
	private String personAnalyseNumByProM;
	private String personAnalyseNumByPersonDayM;
	private String personAnalyseNumByDayM;
	private String personAnalyseNumM;
	
	private String ssvPersonAnalyseNumM;
	private String gridPersonAnalyseNumM;
	private String kpiPersonAnalyseNumM;
	
	private String type;
	private String begindate;
	private String enddate;

	private String begindateM;
	private String enddateM;
	*//**�Ϻ�Ͷ��*//*
	private String complainStatNumByDayM;
	private String complainStatNumTestDateM;
	private String complainStatNumPromotoM;
	private String complainStatNumPositionM;
	private String complainStatNumTestPositionM;
	private String complainStatNumBackStepDateLastM;
	private String complainStatNumAvgTimeDayM;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getComplainStatNumByDayM() {
		return complainStatNumByDayM;
	}
	public void setComplainStatNumByDayM(String complainStatNumByDayM) {
		this.complainStatNumByDayM = complainStatNumByDayM;
	}
	public String getComplainStatNumTestDateM() {
		return complainStatNumTestDateM;
	}
	public void setComplainStatNumTestDateM(String complainStatNumTestDateM) {
		this.complainStatNumTestDateM = complainStatNumTestDateM;
	}
	public String getComplainStatNumPromotoM() {
		return complainStatNumPromotoM;
	}
	public void setComplainStatNumPromotoM(String complainStatNumPromotoM) {
		this.complainStatNumPromotoM = complainStatNumPromotoM;
	}
	public String getComplainStatNumPositionM() {
		return complainStatNumPositionM;
	}
	public void setComplainStatNumPositionM(String complainStatNumPositionM) {
		this.complainStatNumPositionM = complainStatNumPositionM;
	}
	public String getComplainStatNumTestPositionM() {
		return complainStatNumTestPositionM;
	}
	public void setComplainStatNumTestPositionM(String complainStatNumTestPositionM) {
		this.complainStatNumTestPositionM = complainStatNumTestPositionM;
	}
	public String getComplainStatNumBackStepDateLastM() {
		return complainStatNumBackStepDateLastM;
	}
	public void setComplainStatNumBackStepDateLastM(
			String complainStatNumBackStepDateLastM) {
		this.complainStatNumBackStepDateLastM = complainStatNumBackStepDateLastM;
	}
	public String getComplainStatNumAvgTimeDayM() {
		return complainStatNumAvgTimeDayM;
	}
	public void setComplainStatNumAvgTimeDayM(String complainStatNumAvgTimeDayM) {
		this.complainStatNumAvgTimeDayM = complainStatNumAvgTimeDayM;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBegindate() {
		return begindate;
	}
	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getPersonAnalyseNumByProM() {
		return personAnalyseNumByProM;
	}
	public void setPersonAnalyseNumByProM(String personAnalyseNumByProM) {
		this.personAnalyseNumByProM = personAnalyseNumByProM;
	}
	public String getPersonAnalyseNumByPersonDayM() {
		return personAnalyseNumByPersonDayM;
	}
	public void setPersonAnalyseNumByPersonDayM(String personAnalyseNumByPersonDayM) {
		this.personAnalyseNumByPersonDayM = personAnalyseNumByPersonDayM;
	}
	public String getPersonAnalyseNumByDayM() {
		return personAnalyseNumByDayM;
	}
	public void setPersonAnalyseNumByDayM(String personAnalyseNumByDayM) {
		this.personAnalyseNumByDayM = personAnalyseNumByDayM;
	}
	public String getPersonAnalyseNumM() {
		return personAnalyseNumM;
	}
	public void setPersonAnalyseNumM(String personAnalyseNumM) {
		this.personAnalyseNumM = personAnalyseNumM;
	}
	public String getBegindateM() {
		return begindateM;
	}
	public void setBegindateM(String begindateM) {
		this.begindateM = begindateM;
	}
	public String getEnddateM() {
		return enddateM;
	}
	public void setEnddateM(String enddateM) {
		this.enddateM = enddateM;
	}
	public String getSsvPersonAnalyseNumM() {
		return ssvPersonAnalyseNumM;
	}
	public void setSsvPersonAnalyseNumM(String ssvPersonAnalyseNumM) {
		this.ssvPersonAnalyseNumM = ssvPersonAnalyseNumM;
	}
	public String getGridPersonAnalyseNumM() {
		return gridPersonAnalyseNumM;
	}
	public void setGridPersonAnalyseNumM(String gridPersonAnalyseNumM) {
		this.gridPersonAnalyseNumM = gridPersonAnalyseNumM;
	}
	public String getKpiPersonAnalyseNumM() {
		return kpiPersonAnalyseNumM;
	}
	public void setKpiPersonAnalyseNumM(String kpiPersonAnalyseNumM) {
		this.kpiPersonAnalyseNumM = kpiPersonAnalyseNumM;
	}
	
	
	
}
*/