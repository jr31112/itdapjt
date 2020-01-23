<template>
  <div class="recruitdetail">
    
      <detail-company-info :company="recruit.company"/>
    
      <detail-wanted-info :wanted="recruit.wanted" :jobs="recruit.jobs"/>
      <detail-wanted-comment/>
  </div>
</template>

<script>
import DetailCompanyInfo from '../components/DetailCompanyInfo.vue'
import DetailWantedInfo from '../components/DetailWantedInfo.vue'
import DetailWantedComment from '../components/DetailWantedComment.vue'


export default {
  name: 'RecruitDetail',
  components: {
    DetailCompanyInfo,
    DetailWantedInfo,
    DetailWantedComment
  },
  data(){
    return{
      recruit : {
        "company": {
            "cid": 2,
            "corpNm": "㈜KB데이타시스템",
            "totPsncnt": "485명",
            "busiSize": "대기업 계열사·자회사",
            "yrSalesAmt": "1313억",
            "corpAddr": "서울 마포구 도화동 566 경찰공제회 자람빌딩 14층",
            "homePg": "http://www.kds.co.kr",
            "busiCont": "금융IT컨설팅,시스템통합,IT아웃소싱",
            "logo": "http://img.jobkorea.co.kr/trans/c/200x80/k/d/JK_Co_kdsinsa.png"
        },
        "wanted": {
            "wid": 1,
            "wantedTitle": "KB데이타시스템 신입사원 모집 공고",
            "active": 0,
            "startDate": "2020-01-22",
            "endDate": "2020-02-03",
            "process": "http://orasfile.jobkorea.co.kr/orasphoto/public/kbfng/process_2020.jpg",
            "etc": "ㅇ「국가유공자등예우및지원에관한법률」에 의한 취업보호대상자는 우대함., ㅇ 「장애인고용촉진및직업재활법」에 의해 장애인 지원자는 우대함., ㅇ 합격자 발표 등 채용관련 진행사항 및 향후 일정은 채용 홈페이지  통하여 안내될 예정임., ㅇ 지원서 기재사항이 사실과 다른 것으로 판명될 경우 채용 또는 합격이 취소됨., ㅇ 기타 문의사항은 채용 홈페이지 내 ‘문의하기’ 게시판을 이용하여 주시기 바람., ㅇ 접수 마감일은 집중이 예상되니 마감일 이전에 등록하여 주시기 바람.",
            "question": null,
            "vcnt": 0,
            "cid": 2
        },
        "jobs": [
            {
                "jid": 1,
                "to": "00",
                "jname": "IT개발/운영",
                "jdetail": null,
                "jtype": null,
                "require": "ㅇ 학력/전공/연령 제한사항 없음(2020년 2월 졸업예정자의 경우 해당기간 내 졸업 가능자에 한 함),ㅇ 병역필 또는 면제자로서 해외여행에 결격사유가 없는 자",
                "extra": "- IT 관련 전공자, - 국가보훈대상자, 등록장애인, - IT관련 자격증 취득자",
                "place": null,
                "wid": 1
            }
        ]
      }
    }
  }
}
</script>

<style>

</style>