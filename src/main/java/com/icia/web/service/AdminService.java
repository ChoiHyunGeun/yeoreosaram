package com.icia.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.icia.web.dao.AdminDao;
import com.icia.web.model.Admin;
import com.icia.web.model.HiBoard;
import com.icia.web.model.HiBoardFile;
import com.icia.web.model.User2;

@Service("adminService")
public class AdminService 
{
      private static Logger logger = LoggerFactory.getLogger(AdminService.class);
      
      @Autowired
      private AdminDao adminDao;

   //select   
   public Admin adminSelect(String adminId)
   {
      Admin admin=null;
      
      try
      {
         admin = adminDao.adminSelect(adminId);
      }
      catch(Exception e)
      {
         logger.error("[AdminService] adminSelect Exception 오류 확인!", e);
      }
      
      return admin;
    }
   
 
   
 //유저리스트
  public List<Admin> adminList(String adminId)
   {
      List<Admin> adminList = null;
      
      try
      { 
         adminList = adminDao.adminList();
      }
      catch(Exception e)
      {
         logger.error("[AdminService] userList Exception", e);
      }
      
      return  adminList;
   }
  
  //유저 인서트
  public void insertAdmin(Admin admin) {
      
  }
  
  public Admin userSelect(String userId2) {
	  Admin admin = null;
      
      try
      {
         admin = adminDao.userSelect(userId2);
      }
      catch(Exception e)
      {
         logger.error("[AdminService] userSelect Exception", e);
      }
      
      return admin;
  }
  
  //유저 삭제
  public int userDelete(String userId2) {
	  int count = 0;
	  
	 try
	 {
		 count = adminDao.userDelete(userId2);
	 }
	 catch(Exception e)
	 {
		 logger.error("[AdminService] userDelete Exception", e);
	 }
	  
      return count;
   }
  
//동행게시판
  public List<Admin> testList(Admin admin)
   {
      List<Admin> testList = null;
      
      try
      { 
         testList = adminDao.testList(admin);
      }
      catch(Exception e)
      {
         logger.error("[게시판 실패] userList Exception", e);
      }
      
      return  testList;
   }
  //게시물 답변 리스트
  public List<Admin> testReplyList(Admin admin)
  {
     List<Admin> replylist = null;
     
     try
     { 
        replylist = adminDao.testReplyList(admin);
     }
     catch(Exception e)
     {
        logger.error("[HiBoardService] boardList Exception", e);
     }
     
     return  replylist;
  }
  
  //동행게시판 게시물 보기
  public Admin adminView(long hiBbsSeq)
  {
     Admin admin =null;
     
     try {
        
        admin= adminDao.testSelect(hiBbsSeq);
        
     }
     catch(Exception e)
     {
        logger.error("[AdminService] adminView Exception", e);
     }
    
     return admin;
  }
  
  //게시물 조회
  public Admin testSelect(long hiBbsSeq)
  {
     Admin admin =null;
     
     try
     {
        admin = adminDao.testSelect(hiBbsSeq);
     }
     catch(Exception e)
     {
        logger.error("[AdminService] testSelect Exception", e);
     }
     
     return admin;
  }
  /*
  //게시물 삭제
  public int adminListDelete(long hiBbsSeq)
  {
     int count =0;
     
     Admin admin=adminView(hiBbsSeq);
     
     if(admin!=null)
     {
        count = adminDao.adminListDelete(hiBbsSeq);
       
     }
     
     return count;
  }*/

//동행게시물 삭제
  @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
  public int adminListDelete(long hiBbsSeq) throws Exception
  {
     int count =0;
     
     Admin admin=adminView(hiBbsSeq);
     
     if(admin!=null)
     {
        count = adminDao.adminListDelete(hiBbsSeq);
       
     }
     return count;
  }
  
  //동행게시판 댓글 삭제
  @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
  public int adminReplyDelete(long hiBbsSeq, long hiBbsOrder) throws Exception
  {
     int count = 0;
     
     Admin parentAdmin = adminView(hiBbsSeq);
     
     if(parentAdmin != null)
     {
        count = adminDao.adminReplyDelete(parentAdmin);
        logger.debug("[서비스] 댓삭 게시물 번호 : "+ hiBbsSeq);
        logger.debug("[댓글순서 order 번호] : "+ hiBbsOrder);
     }
     
     return count;
  }
  //고객센터 게시판
  public List<Admin> qList(Admin admin)
   {
      List<Admin> qList = null;
      
      try
      { 
         qList = adminDao.qList(admin);
      }
      catch(Exception e)
      {
         logger.error("[게시판 실패] userList Exception", e);
      }
      
      return  qList;
   } 
//고객게시판 게시물 보기
  public Admin adminqView(long qnaHiBbsSeq)
  {
     Admin admin =null;
     
     try {
        
        admin= adminDao.qSelect(qnaHiBbsSeq);
        
     }
     catch(Exception e)
     {
        logger.error("[AdminService] adminqView Exception", e);
     }
    
     return admin;
  }
  
  //고객센터 게시물 조회
  public Admin qSelect(long qnaHiBbsSeq)
  {
     Admin admin =null;
     
     try
     {
        admin = adminDao.qSelect(qnaHiBbsSeq);
     }
     catch(Exception e)
     {
        logger.error("[AdminService] qSelect Exception", e);
     }
     
     return admin;
  }
  
//고객센터 게시물 삭제
  @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
  public int adminqListDelete(long qnaHiBbsSeq) throws Exception
  {
     int count =0;
     
     Admin admin=adminqView(qnaHiBbsSeq);
     
     if(admin!=null)
     {
        count = adminDao.adminqListDelete(qnaHiBbsSeq);
       
     }
     return count;
  }
   
//게시물 답글 등록
  @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
  public int adminReplyInsert(Admin admin) throws Exception
  {
     int count = adminDao.adminReplyInsert(admin);
     
     return count;
  }
  
  
  
}