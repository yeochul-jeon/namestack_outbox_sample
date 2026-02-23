# or-cst-svc

| 대상 엔드포인트 또는 서비스명 | 주석 내용 요약(한글로 출력) | 컨트롤러 명 | 데이터베이스 | 테이블 |
|---|---|---|---|---|
| /b2bcust/b2bcust/retrieveCustLoanBndHist | 고객 여신 한도 이력 조회 | B2bCustController | Oracle | ADM.TB2BPRTNRLOANHIST |
| /b2bcust/b2bcust/retrieveCustLoanBndAvg | 고객 여신 한도 평균 조회 | B2bCustController | Oracle | ADM.TB2BPRTNRLOANHIST |
| /b2bcust/b2bcust/retrieveB2bCustLst | B2B 거래처 목록 조회 | B2bCustController | Oracle | ADM.TB2BPRTNR, ADM.TCUSTOMER, ADM.TPERINFO, ADM.TDEPT |
| /b2bcust/b2bcust/retrieveB2bBcncByBizusrNoLst | B2B 거래처 BY 사업자 번호 목록 조회 | B2bCustController | Oracle | ADM.TB2BPRTNR |
| /b2bcust/b2bcust/modifyB2bBcncByBizusrNoCustStat | B2B 거래처 BY 사업자 번호 고객 상태 수정 | B2bCustController | Oracle | ADM.TB2BPRTNR |
| /b2bcust/b2bcust/retrieveB2bCustVirtAcnt | B2B 고객 가상 계좌 조회 | B2bCustController | Oracle | ADM.TVIRTUALACNT, ADM.TINAMTBANK |
| /b2bcust/b2bcust/retrieveB2bCust | B2B 고객 거래처 조회 | B2bCustController | Oracle | ADM.TB2BPRTNR, ADM.TCUSTOMER, ADM.TINAMTBANK |
| /b2bcust/b2bcust/saveInamtVirtAcntNo | 입금가상계좌번호를 저장한다. | B2bCustController | Oracle | ADM.TB2BPRTNR |
| /b2bcust/b2bcust/saveB2bCustBcnc | B2B고객을 등록한다. | B2bCustController | Oracle | ADM.TB2BPRTNR |
| /batchmng/ondemandbatch/callOnlineBatch | 온라인배치 호출 | OnlineBatchController | 존재하지 않음 | 존재하지 않음 |
| /batchmng/ondemandbatch/callPromotionOnlineBatch | 온라인배치 호출 md-prd | OnlineBatchController | 존재하지 않음 | 존재하지 않음 |
| /cjonstyle/custcjonstyleInf/saveCustCjonstyleIdCrteDeny | 고객 onstyle ID 생성을 거부한다. ( 전환거부버튼 ) | CustCjonstyleInfController | 존재하지 않음 | 존재하지 않음 |
| /cjonstyle/custcjonstyleInf/saveCustCjonstyleIdCrte | 고객 Cjonstyle ID를 생성한다. ( cjonstyle API 호출 포함 ) | CustCjonstyleInfController | 존재하지 않음 | 존재하지 않음 |
| /cjonstyle/custcjonstyleInf/confirmCustonstyleIdDup | 고객 onstyle ID 중복을 확인한다. ( cjonstyle API 내용을 추가하여 개발한다. ) | CustCjonstyleInfController | 존재하지 않음 | 존재하지 않음 |
| /cjonstyle/custcjonstyleInf/resetCustCjonstylePw | 고객 Cjonstyle PW를 초기화한다. ( cjonstyle API 내용을 추가하여 개발한다. ) | CustCjonstyleInfController | 존재하지 않음 | 존재하지 않음 |
| /common | 존재하지 않음 | CommonController | 존재하지 않음 | 존재하지 않음 |
| /custconfig/config/retrieveConfigList | 존재하지 않음 | UserServiceConfigController | Oracle | TB_USER_SERVICE_CONFIG |
| /custconfig/config/retrieveReloadRate | 존재하지 않음 | UserServiceConfigController | Oracle | TB_USER_SERVICE_CONFIG |
| /custconfig/config/modifyReloadRate | 존재하지 않음 | UserServiceConfigController | Oracle | TB_USER_SERVICE_CONFIG |
| /custconfig/config/saveConfigList | 존재하지 않음 | UserServiceConfigController | Oracle | TB_USER_SERVICE_CONFIG |
| /custconts/custCntr/retrieveGongjiList | 공지사항목록을 조회한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M, ADM.TPERINFO, ADM.TCODE, MALLOWN.TGONGJI_EXPS_LOC |
| /custconts/custCntr/saveGongjiList | 공지사항목록을 저장한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M |
| /custconts/custCntr/retrieveGongji | 공지사항을 조회한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M, ADM.TPERINFO, ADM.TCODE, MALLOWN.TGONGJI_EXPS_LOC, MALLOWN.TGONGJI_D |
| /custconts/custCntr/saveGongji | 공지사항을저장한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M, MALLOWN.TGONGJI_D |
| /custconts/custCntr/deleteGongji | 공지사항을 삭제한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M, MALLOWN.TGONGJI_D, MALLOWN.TGONGJI_EXPS_LOC |
| /custconts/custCntr/retrieveGongjiPreviewUrl | 공지사항미리보기URL을 조회한다. | CustCntrController | Oracle | MALLOWN.TGONGJI_M |
| /custconts/custCntr/retrieveFaqList | FAQ목록을 조회한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM, MALLOWN.TCUSTBBSCONT, MALLOWN.TFAQ_MANAGER, ADM.TPERINFO, ADM.TCODE |
| /custconts/custCntr/deleteFaqList | FAQ목록을 삭제한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM, MALLOWN.TFAQ_MANAGER |
| /custconts/custCntr/resetAllFaqReadCnt | 모든 FAQ 조회수를 초기화한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM |
| /custconts/custCntr/retrieveFaq | FAQ를 조회한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM, MALLOWN.TFAQ_MANAGER, MALLOWN.TCUSTBBSCONT, MALLOWN.TCUSTBBSRECONT, ADM.TPERINFO, ADM.TCODE |
| /custconts/custCntr/saveFaq | FAQ를 저장한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM, MALLOWN.TCUSTBBSCONT, MALLOWN.TCUSTBBSRECONT, MALLOWN.TFAQ_MANAGER |
| /custconts/custCntr/deleteFaq | FAQ를 삭제한다. | CustCntrController | Oracle | MALLOWN.TCUSTBBSM, MALLOWN.TCUSTBBSCONT, MALLOWN.TCUSTBBSRECONT, MALLOWN.TFAQ_MANAGER |
| /custconts/custConts/retrieveLegalNoticeList | 법적고지목록을 조회한다. | CustContsController | Oracle | MALLOWN.TLEGAL_NOTICE, ADM.TCODE |
| /custconts/custConts/updateLegalNoticeDispInfList | 법적고지전시정보목록을 저장한다. | CustContsController | Oracle | MALLOWN.TLEGAL_NOTICE |
| /custconts/custConts/retrieveLegalNotice | 법적고지을 조회한다. | CustContsController | Oracle | MALLOWN.TLEGAL_NOTICE, ADM.TCODE |
| /custconts/custConts/saveLegalNotice | 법적고지를저장한다. | CustContsController | Oracle | MALLOWN.TLEGAL_NOTICE |
| /custconts/custConts/retrieveLegalNoticePreviewUrl | 법적고지미리보기URL을 조회한다. | CustContsController | Oracle | MALLOWN.TLEGAL_NOTICE |
| /custinfo/acctLog/retrieveAcctLogInfList | 회원통합로그를 조회한다. | AcctLogController | Oracle | MALLOWN.TACCOUNT_LOG, ADM.TCODE |
| /custinfo/acctLog/retrieveWebLoginLogList | 웹로그인이력을 조회한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN_LOG |
| /custinfo/acctLog/retrieveKcbCertLogList | KCB인증 이력을 조회한다 | AcctLogController | Oracle | SUPPUSR.TAUTH_CHECK_LOG |
| /custinfo/acctLog/retrieveKmcCertLogList | KMC인증 이력을 조회한다 | AcctLogController | Oracle | ADM.T_KMCIS_WEB_LOG |
| /custinfo/acctLog/retrieveSmsMsgLogList | SMS점유인증 이력을 조회한다 | AcctLogController | Oracle | ADM.TSMSRECEIVER, ADM.TSMSMSG |
| /custinfo/acctLog/retrieveTcustdatahistList | 고객정보변경이력 조회한다. | AcctLogController | Oracle | ADM.TCUSTDATAHIST |
| /custinfo/acctLog/retrieveTpwdChgHistList | 비밀번호 변경 HISTORY 테이블 정보목록 조회한다. | AcctLogController | Oracle | MALLOWN.TPWD_CHG_HIST |
| /custinfo/acctLog/retrieveSmsMsgSendCountList | 점유인증 발송 건수 조회한다 | AcctLogController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/acctLog/retrieveWebAutoLoginList | 웹자동로그인관리정보를 조회한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN |
| /custinfo/acctLog/retrieveWebLoginUidList | 웹로그인UID를 조회한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN_UID |
| /custinfo/acctLog/updateWebAutoLoginList | 자동웹로그인정보를 수정한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN |
| /custinfo/acctLog/updateWebAutoLoginUidList | 웹로그인UID관리정보를 수정한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN_UID |
| /custinfo/acctLog/retrieveTwebLoginAdd | 해외로그인차단정보 조회한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN_ADD |
| /custinfo/acctLog/updateTwebLoginAdd | 해외로그인차단정보 수정한다. | AcctLogController | Oracle | MALLOWN.TWEB_LOGIN_ADD |
| /custinfo/acctLog/retrieveCustRcvHistList | 고객가입이력을 조회한다 | AcctLogController | Oracle | ADM.TCUSTRCVHIST, ADM.TRCVPATH |
| /custinfo/acctLog/retrieveMsgSendHistList | 메시지발송이력을 조회한다 | AcctLogController | Oracle | ADM.TSMSRECEIVER, ADM.TSMSMSG, ADM.TMMSRECEIVER, ADM.TMMSMSG, ADM.TKAKAORECEIVER, ADM.TKAKAOMSG, ADM.TCODE |
| /custinfo/acctLog/retrieveEmailCertLogList | 메일점유인증 이력을 조회한다 | AcctLogController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/acctSelectGrp/retrieveAcctSelectGrpList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보를 조회한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP, ADM.TACCTSELECTGRPDTL |
| /custinfo/acctSelectGrp/registerAcctSelectGrpList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보를 수정하거나 등록한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP |
| /custinfo/acctSelectGrp/deleteAcctSelectGrpList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보를 삭제한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP |
| /custinfo/acctSelectGrp/retrieveAcctSelectGrpOfferCd | 선별그룹 내 오퍼코드 리스트를 조회한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP |
| /custinfo/acctSelectGrp/registerOfferCdList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보 중 오퍼코드를 수정하거나 등록한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP |
| /custinfo/acctSelectGrp/deleteOfferCdList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보 중 오퍼코드를 삭제한다. | AcctSelectGrpController | Oracle | ADM.TACCTSELECTGRP |
| /custinfo/acctSelectGrp/retrieveCustReviewNotify | 온트러스트 선별그룹조회 모집알림 신청 화면에서 정보를 조회한다. | AcctSelectGrpController | Oracle | ADM.TCUST_REVIEW_NOTIFY, ADM.TCUSTOMER |
| /custinfo/acctSelectGrpDtl/retrieveSelectGrpList | 선별그룹의 목록을 조회한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL, ADM.TACCTSELECTGRP, ADM.TCUSTOMER |
| /custinfo/acctSelectGrpDtl/retrieveAcctSelectGrpDtlList | 온트러스트 선별그룹조회상세 화면에서 선별그룹 내 고객 정보를 조회한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL, ADM.TACCTSELECTGRP, ADM.TCUSTOMER |
| /custinfo/acctSelectGrpDtl/retrieveWebSurveyGrpDtlList | 온트러스트 선별그룹조회상세 화면에서 웹서베이그룹 내 고객 정보를 조회한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL, ADM.TACCTSELECTGRP, ADM.TCUSTOMER |
| /custinfo/acctSelectGrpDtl/retrieveSurveyGrpDtlList | 온트러스트 선별그룹조회상세 화면에서 설문그룹 내 고객 정보를 조회한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL, ADM.TACCTSELECTGRP, ADM.TCUSTOMER |
| /custinfo/acctSelectGrpDtl/registerAcctSelectGrpDtlList | 온트러스트 선별그룹조회 화면에서 선별그룹 정보를 수정하거나 등록한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL |
| /custinfo/acctSelectGrpDtl/registerSurveyGrpList | 온트러스트 선별그룹조회 화면에서 설문그룹 내 정보를 수정하거나 등록한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL |
| /custinfo/acctSelectGrpDtl/updateAcctSelectGrpDtlUseYn | 온트러스트 선별그룹조회상세 화면에서 선별그룹 내 고객의 사용여부를 N으로 변경한다. | AcctSelectGrpDtlController | Oracle | ADM.TACCTSELECTGRPDTL |
| /custinfo/cjOffclInfo/retrieveCjOffclInfList | 온트러스트 임직원조회 화면에서 임직원 정보를 조회한다. | CjOffclInfoController | Oracle | ADM.TCJMEMBER, ADM.TGRPCOMP, ADM.TCUSTOMER |
| /custinfo/cjOffclInfo/retrieveCjmembCardList | CJ임직원카드 목록을 조회한다. | CjOffclInfoController | Oracle | ADM.TCJMEMB_CARD, ADM.TCJCOMP_CARD, ADM.TGRPCOMP, ADM.TCJMEMBER |
| /custinfo/cjOffclInfo/modifyCjOffclCustNo | CJ임직원 고객번호를 수정한다. | CjOffclInfoController | Oracle | ADM.TCJMEMBER |
| /custinfo/cjOffclInfo/modifyCjmembCardRecordModidate | CJ임직원카드수정일시를 현재일시로 변경한다. | CjOffclInfoController | Oracle | ADM.TCJMEMB_CARD |
| /custinfo/cjOffclInfo/retrieveCjOffclCmpndList | CJ임직원+CJ임직원카드 두 목록을 조횧한다 | CjOffclInfoController | Oracle | ADM.TCJMEMBER, ADM.TCJMEMB_CARD, ADM.TCJCOMP_CARD, ADM.TGRPCOMP |
| /custinfo/cjOffclInfo/retrieveCjOffclCiInfo | 본인인증정보 일치여부 판단시 사용 | CjOffclInfoController | Oracle | ADM.TCJEMP_GHR, ADM.TCJEMP_GHR_CUST, ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /custinfo/cjOffclInfo/retrieveCjOffclHistInfo | GHR 임직원 변경 내역 | CjOffclInfoController | Oracle | ADM.TCJEMP_GHR_HIST |
| /custinfo/cjOffclInfo/retrieveGrpCompList | 계열사 목록 조회 한다. | CjOffclInfoController | Oracle | ADM.TGRPCOMP |
| /custinfo/cjOffclInfo/saveGrpCompList | 계열사목록저장 | CjOffclInfoController | Oracle | ADM.TGRPCOMP |
| /custinfo/cjOffclInfo/retrieveCjCompCardList | CJ회사카드목록 조회 한다. | CjOffclInfoController | Oracle | ADM.TCJCOMP_CARD |
| /custinfo/cjOffclInfo/modifyCjCompCardRepCompanyCodeList | CJ회사카드의 대표회사코드 목록수정 | CjOffclInfoController | Oracle | ADM.TCJCOMP_CARD |
| /custinfo/cjOffclInfo/retrieveCjoneInfoList | CJONE 통합회원 정보 조회 | CjOffclInfoController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/cjOffclSaveamt/retrieveCjOffclSaveamtHistList | 정산관리>임직원적립금이력조회 | CjOffclSaveamtController | Oracle | ADM.TCJMEMBER, ADM.TSAVEAMTUSE, ADM.TSAVEAMTLINK, ADM.THSAVEAMTGIVE, ADM.TCODE, ADM.TOFFER |
| /custinfo/cjOffclSaveamt/downloadCjOffclSaveamtHistListAll | 임직원적립금사용내역을 조회하여 엑셀다운로드한다. | CjOffclSaveamtController | Oracle | ADM.TCJMEMBER, ADM.TSAVEAMTUSE, ADM.TSAVEAMTLINK, ADM.THSAVEAMTGIVE, ADM.TCODE, ADM.TOFFER |
| /custinfo/custAddr/retrieveB2bCustAddrCndtn | 고객번호 및 고객 조회 조건에 따라 B2B 고객 주소 정보를 조회한다.( 전화번호(1), 고객명(2), 주문번호(3), 고객번호(4) ) | CustAddrController | Oracle | ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TSINGCUST, ADM.TB2BPRTNR, ADM.TORDER |
| /custinfo/custAddr/retrieveCustAddrCndtnLst | 고객 주소를 조건 별로 조회한다. | CustAddrController | Oracle | ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TCUSTOMER_DIV |
| /custinfo/custAddr/retrieveCustBasAddr | 고객 기본 주소를 조회한다. | CustAddrController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER |
| /custinfo/custAddr/retrieveCustAddrLst | 고객 주소 목록을 조회한다. | CustAddrController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER |
| /custinfo/custAddr/retrieveCustAddr | 고객 주소를 조회한다. | CustAddrController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER |
| /custinfo/custAddr/retrieveLnmAddr | ROAD_ADDR_ID로 지번주소를 조회한다.( 신규 ) | CustAddrController | Oracle | ADM.TADDR_LNM |
| /custinfo/custAddr/retrieveSmsSendHpNoAsArs | ARS SMS 전송용 핸폰번호을 조회한다. ( ARS 요청 ) | CustAddrController | Oracle | ADM.TCUSTADDR |
| /custinfo/custAddr/retrieveCustDupHpNo | 고객의 중복 핸드폰 번호가 존재하는지 조회한다. | CustAddrController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER |
| /custinfo/custAdiInfo/saveCustAdiInfByRefAgd | 고객 참조 사항을 저장한다. | CustAdiInfController | Oracle | ADM.TCUSTETC |
| /custinfo/custAdiInfo/retrieveCustAdiInfByRefAgdHist | 고객 참조 사항 이력을 조회한다. | CustAdiInfController | Oracle | ADM.TCUSTDATAHIST, ADM.TPERINFO |
| /custinfo/custAdiInfo/retrieveCustAdiInfByRefAgd | 고객 참조 사항을 조회한다. | CustAdiInfController | Oracle | ADM.TCUSTETC, ADM.TSINGCUST |
| /custinfo/custAdiInfo/retrieveCustErrInfYn | 고객 오류정보가 존재하는 지 체크한다.고객 오류정보가 존재할 경우, 고객조회팝업 화면 로딩 시 팝업 메시지를 보여준다. ( AS-IS : custMastMan/O_CustDataErrorNotice_P.xml ) | CustAdiInfController | Oracle | ADM.TCUSTDATAERROR |
| /custinfo/custAdiInfo/retrieveArsErrCd | ARS 오류 코드를 조회한다. | CustAdiInfController | Oracle | ADM.TARSINAMT |
| /custinfo/custAdiInfo/saveImprtncHrOrd | 중요 인사 주문 내역에 대한 SCM확인내역, CS확인내역, 품질확인내역을 저장한다. | CustAdiInfController | Oracle | ADM.TORDERPROC, ADM.TORDERDTL, ADM.TKEYMAN, ADM.TKEYMANORDERDTL |
| /custinfo/custAdiInfo/retrieveImprtncHrOrd | 중요 인사 주문 정보를 조회한다. | CustAdiInfController | Oracle | ADM.TORDERPROC, ADM.TORDERDTL, ADM.TKEYMAN, ADM.TKEYMANORDERDTL, ADM.TWBILL, ADM.TWBILLDTL, ADM.TITEM, ADM.TITEMCHNL, ADM.TRECEIVER |
| /custinfo/custAdiInfo/retrieveImprtncHrInf | 중요 인사 여부를 조회한다. | CustAdiInfController | Oracle | ADM.TKEYMAN |
| /custinfo/custAdiInfo/saveImprtncHrInf | 중요 인사 여부를 저장한다. | CustAdiInfController | Oracle | ADM.TKEYMAN |
| /custinfo/custAdiInfo/retrieveVipCustProcAuth | VI 고객 권한을 조회한다. | CustAdiInfController | Oracle | ADM.TCBCODE |
| /custinfo/custAdiInfo/retrieveCustPayMethRead | 환불계좌조회, 가상계좌조회, 고객결제수단카드를 조회한다. | CustAdiInfController | Oracle | ADM.TCUSTPAM, ADM.TBANK, ADM.TVIRTUALACNT, ADM.TCARDDTL, ADM.TCUSTCAGREE |
| /custinfo/custAdiInfo/saveCustRefundAcntAgr | 결제API IN/OUT DTO를 이용하여 고객환불계좌 동의내역 저장한다. | CustAdiInfController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custAdiInfo/registerCardBrthdtProvAgr | 결제API IN/OUT DTO를 이용하여 고카드생년월일동의저장한다. | CustAdiInfController | 존재하지 않음 | 존재하지 않음 |
| /custAdiInfo/custAdiInfo/saveCustDataErrInf | 고객오류항목을 저장한다. | CustAdiInfController | Oracle | ADM.TCUSTDATAERROR |
| /custAdiInfo/custAdiInfo/retrieveCustDataErrInf | 고객오류항목을 조회한다. | CustAdiInfController | Oracle | ADM.TCUSTDATAERROR |
| /custinfo/custAdiInfo/retrieveOrdNoAsWbNo | 운송장번호로 주문번호를 조회한다. | CustAdiInfController | Oracle | ADM.TWBILL, ADM.TWBILLDTL |
| /custinfo/custAdiInfo/retrieveArsOrdInf | ARS 주문 정보를 조회한다. | CustAdiInfController | Oracle | ADM.TARSORDER, ADM.TARSORDERITEM |
| /custinfo/custAdiInfo/saveOverseasCusclearSmbol | 해외통관부호저장 | CustAdiInfController | Oracle | MALLOWN.TORDER_VEN_KEY |
| /custinfo/custAdiInfo/retrieveOverseasCusclearSmbol | 해외통관부호조회 | CustAdiInfController | Oracle | MALLOWN.TORDER_VEN_KEY |
| /custinfo/custAdiInfo/saveTelNoAndStoreDoNotCall | 두낫콜 등록 자동화 - 휴대폰 번호를 받으면 두낫콜 처리 | CustAdiInfController | Oracle | MALLOWN.TDO_NOT_CALL, ADM.TCUSTOMER, ADM.TCUSTADDR |
| /custinfo/custAdiInfo/retreiveDoNotCallRegCust | 두낫 콜 등록 처리 내역을 조회한다. | CustAdiInfController | Oracle | ADM.TDONOTCALL |
| /custinfo/custAdiInfo/saveDoNotCallRegCust | 두낫 콜 등록 처리 내역을 저장한다. | CustAdiInfController | Oracle | ADM.TDONOTCALL |
| /custinfo/custAdiInfo/retreiveDoNotCallCust | 두낫 콜 등록 고객을 조회한다. | CustAdiInfController | Oracle | ADM.TDONOTCALL |
| /custinfo/custbbs/retrieveCustBbsContInfList | 고객게시판 현황관리목록 조회한다. | CustBbsController | Oracle | ADM.TMALLBOARD, ADM.TMALLBOARDCONT, ADM.TMALLBOARDPROC, ADM.TCUSTOMER, ADM.TCODE, ADM.TSCWORKPART, ADM.TPERINFO, ADM.TWORKPART, ADM.TCCWORKPART |
| /custinfo/custbbs/retrieveCustBbsContInf | 고객게시판 현황관리 조회한다. | CustBbsController | Oracle | ADM.TMALLBOARD, ADM.TMALLBOARDCONT, ADM.TMALLBOARDPROC, ADM.TORDERDTL, ADM.TITEMCHNL, ADM.TCODE, ADM.TPERINFO |
| /custinfo/custbbs/retrieveCustBbsEstInfList | 게시판 만족도 평가 조회한다. | CustBbsController | Oracle | ADM.TMALLBOARD, ADM.TMALLBOARDCONT, ADM.TMALLBOARDPROC, ADM.TSCWORKPART, ADM.TPERINFO, ADM.TWORKPART, ADM.TCCWORKPART, ADM.TCODE |
| /custinfo/custbbs/downloadCustBbsContInfListAll | 고객게시판 현황관리목록을 액셀 다운로드한다. | CustBbsController | Oracle | ADM.TMALLBOARD, ADM.TMALLBOARDCONT, ADM.TMALLBOARDPROC, ADM.TCUSTOMER, ADM.TCODE, ADM.TSCWORKPART, ADM.TPERINFO, ADM.TWORKPART, ADM.TCCWORKPART |
| /custinfo/custbbs/downloadCustBbsEstInfListAll | 게시판 만족도 평가목록을 액셀 다운로드한다. | CustBbsController | Oracle | ADM.TMALLBOARD, ADM.TMALLBOARDCONT, ADM.TMALLBOARDPROC, ADM.TSCWORKPART, ADM.TPERINFO, ADM.TWORKPART, ADM.TCCWORKPART, ADM.TCODE |
| /custinfo/custCert/retrieveEasyJoinCust | 간편 가입 고객을 조회한다. | CustCertEtcController | Oracle | ADM.TCUSTOMER, ADM.TCUSTADDR |
| /custinfo/custCert/saveCustCertDeny | 실명인증 거부로 저장한다. | CustCertEtcController | Oracle | ADM.TCUSTOMER |
| /custinfo/custCert/modifyCustCertStatAsHp | 고객 핸드폰 인증 후 고객 인증 상태를 수정한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/saveCustCert | 고객 인증 정보를 저장한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/requestRealnmCertNo | 한국모바일인증 API를 호출하여 실명인증번호를 요청한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/transmitRealnmCertNoRe | 한국모바일인증 API를 호출하여 실명인증번호를 재요청한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/confirmRealnmCertNo | 한국모바일인증 API를 호출하여 생성된 실명인증번호를 확인한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/confirmRealnmCertNoNew | 한국모바일인증 API를 호출하여 생성된 실명인증번호를 확인한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custCert/saveHpCertRstLog | 핸드폰 인증 확인 결과 로그 정보를 저장한다. | CustCertEtcController | Oracle | ADM.T_KMCIS_WEB_LOG |
| /custinfo/custCert/checkCiAfterCert | 본인인증 후 CI로 기존 고객을 검증한다. | CustCertEtcController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custDelivplc/saveCustDelivplc | 고객배송지를 등록, 수정한다. | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/retrieveCustDelivplcLst | 고객배송지를 조회한다. | CustDelivplcController | Oracle | ADM.TRECEIVER, ADM.TCODE |
| /custinfo/custDelivplc/retrieveCustDelivplcPageLst | 고객배송지를 조회한다. | CustDelivplcController | Oracle | ADM.TRECEIVER, ADM.TCODE |
| /custinfo/custDelivplc/retrieveCustDelivplcAddrModHist | 고객 배송지 주소 변경 이력을 조회한다. | CustDelivplcController | Oracle | ADM.TRECEIVER, ADM.TPERINFO |
| /custinfo/custDelivplc/retrieveCustDelivplcSeq | 고객 배송지 순번을 생성한다. ( 카카오톡 요청 ) | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/retrieveNombrCustDelivplcLst | 비회원 고객 배송지를 조회한다. ( 주문 요청 ) | CustDelivplcController | Oracle | ADM.TRECEIVER, ADM.TCODE, ADM.TORDERDTL |
| /custinfo/custDelivplc/saveCustBasDelivplc | 고객 기본 배송지를 저장한다. | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/saveCustNewDelivplcAsArs | 고객 신규 배송지를 저장한다. ( ARS 요청 ) | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/retrieveCustReceiverList | 고객 배송지목록을 조회한다(노출여부포함) | CustDelivplcController | Oracle | ADM.TRECEIVER, ADM.TCODE, ADM.TZIPPLACE, ADM.TRECEIVER_CVS, ADM.TRECEIVER_SAFE, ADM.TCBCODE |
| /custinfo/custDelivplc/retrieveOwnHomWrcReceiverCountList | 자택,직장 배송지구분 건수를 조회한다 | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/saveCustReceiverList | 고객배송지통합저장 | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/retrieveCustDefaultDelivplc | 고객기본배송지 조회 | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custDelivplc/resetCustDefaultDelivplc | 고객기본배송지 '없음'으로 변경 | CustDelivplcController | Oracle | ADM.TRECEIVER |
| /custinfo/custInfo/retrieveCiDupCust | CI값 중복이 존재하는 고객 내역을 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /custinfo/custInfo/retrieveCustNmInfModHist | 고객 상세 등록 화면에서 조회한다. ( 고객이름변경이력을 조회한다. ) | CustInfoController | Oracle | ADM.TCUSTDATAHIST, ADM.TPERINFO |
| /custinfo/custInfo/retrieveCustDtlInf | 고객 상세 정보를 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER, ADM.TCUSTETC, ADM.TCUSTORDERSUM, ADM.TSEGCUST, ADM.TRCVPATH, ADM.TOBAGREE, ADM.TPERINFO, ADM.TVOC, ADM.TSRPROC, ADM.TCODE |
| /custinfo/custInfo/retrieveCustPrfrItemgrp | 고객 선호 상품을 조회한다. | CustInfoController | Oracle | ADM.TCUSTITEM, ADM.TCHANNEL, ADM.TITEMKINDS |
| /custinfo/custInfo/retrieveCustRsltInf | 고객 상세 등록 화면에서 고객 실적 정보를 조회한다. | CustInfoController | Oracle | ADM.TCUSTORDERSUM |
| /custinfo/custInfo/retrieveCustTelNoModHist | 고객 전화번호 변경 이력을 조회한다. | CustInfoController | Oracle | ADM.TCUSTDATAHIST, ADM.TPERINFO, ADM.TCUSTADDR |
| /custinfo/custInfo/retrieveCustInf | 고객 정보를 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustChnbyRslt | 고객 상세 등록 화면에서 채널별 실적을 조회한다. | CustInfoController | Oracle | ADM.TORDERITEM, ADM.TORDERDTL, ADM.TCODE, ADM.TCUSTORDERSUM |
| /custinfo/custInfo/retrieveOffclInf | 임직원 정보를 조회한다. | CustInfoController | Oracle | ADM.TCJMEMBER |
| /custinfo/custInfo/createCustNo | 고객 번호를 생성한다. ( ARS요청 ) | CustInfoController | Oracle | DUAL |
| /custinfo/custInfo/retrieveB2bCustYn | B2B 고객 여부를 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER, ADM.TB2BPRTNR |
| /custinfo/custInfo/saveCustDtl | 고객 상세 정보를 저장한다. (고객 상세 등록 ) | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveDepstr | 예금주 조회 ( 고객명을 조회한다. ARS 요청 건으로 예금주 조회로 만들어 달라고 함. ) | CustInfoController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custInfo/saveCustMain | 고객 정보를 등록, 수정한다. (고객조회팝업) | CustInfoController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custInfo/checkCustYnAsCustNoOrBrthdt | 고객 여부를 법정생년월일, 고객번호로 체크한다. (ARS요청 ) | CustInfoController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER, ADM.TSEGCUST, ADM.TCUSTOMER_DIV |
| /custinfo/custInfo/checkCustYnAsTelNo | 고객 여부를 전화번호로 체크한다. (ARS요청) | CustInfoController | Oracle | ADM.TCUSTADDR, ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustDrmncyYn | 고객 휴면 여부를 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCiDupCustYn | CI 중복 고객 여부를 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/saveB2bCust | B2B 고객을 저장한다. ( B2B 고객 조회 팝업 ) | CustInfoController | 존재하지 않음 | 존재하지 않음 |
| /custinfo/custInfo/resetCustCiInf | 고객 ci정보를 null로 수정한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustList | 고객 목록을 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER, ADM.TCODE, MALLOWN.TWEB_ACCOUNT, ADM.TSEGCUST |
| /custinfo/custInfo/synchronizeCustCjonstyleId | 고객 CJ온스타일ID를 동기화한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/resetCustCertInf | CI를 포함한 고객 인증정보를 초기화한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/modifyCustBirthdate | 법정생년월일수정 | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustSiteType | 고객 SITE_TP_CD 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustAsCndtn | 고객을 조건에 따라 조회한다. | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custinfo/custInfo/retrieveCustAdd | 고객부가정보를 조회한다. | CustInfoController | Oracle | ADM.TCUSTADD |
| /custinfo/custInfo/resetCustAdd | 고객부가정보를 초기화한다. | CustInfoController | Oracle | ADM.TCUSTADD |
| /custinfo/custInfo/retrieveCustStat | 고객상태정보조회 | CustInfoController | Oracle | ADM.TCUSTOMER |
| /custmng/custBenefit/alreadyDownloaded | 이달의 등급혜택(쿠폰, 선할인)을 다운로드 받았는지 확인한다. | CustBenefitController | Oracle | ADM.TGRADEDCGIVE |
| /custmng/custBenefit/recallGradeBenefit | 이달의 등급혜택(쿠폰, 선할인) 정보를 회수한다. | CustBenefitController | Oracle | ADM.TGRADEDCGIVE |
| /custmng/custDrmncy/modifyArsCustDrmncyRecovry | ARS 고객 휴면 복구를 수정한다. ( 카카오톡 요청, AS-IS In/Out 파라미터 동일하게 요청함. ) | CustDrmncyController | Oracle | ADM.TCUSTOMER_DIV |
| /custmng/custDrmncy/modifyCustLastLdinInf | 휴면회원으로 등록한다. | CustDrmncyController | 존재하지 않음 | 존재하지 않음 |
| /custmng/custDrmncy/retrieveCustLastLdinInf | 고객 최종 로그인 정보를 조회한다. | CustDrmncyController | Oracle | ADM.TCUSTLASTLOGIN, ADM.TCUSTOMER, ADM.TCONTACT, ADM.TORDERDTL, ADM.TVOC, ADM.TCOUNORDER |
| /custmng/custDrmncy/modifyCustDrmncyRecovry | 고객휴면을 복원한다. | CustDrmncyController | Oracle | ADM.TCUSTOMER_DIV |
| /custmng/custDrmncy/modifyIntgMemDrmncyRecovry | 결제배치에서 통합 회원의 휴면 복구를 수정한다.(결제요청, 2021.10.25) | CustDrmncyController | 존재하지 않음 | 존재하지 않음 |
| /custmng/custDrmncy/retrieveDrmncyTranSplitCustAddrCndtnLst | 고객의 분리 이관 주소 정보를 조회한다. | CustDrmncyController | Oracle | ADM.TCUSTOMER_DIV, ADM.TCUSTADDR_DIV, ADM.TSINGCUST, ADM.TCODE, ADM.TRCVPATH, ADM.TREL_NO_USE, ADM.TWBILL, ADM.TORDER |
| /custmng/custDrmncy/retrieveDrmncyTranSplitCustDtlInf | 분리 이관 고객 상세 정보를 조회한다. | CustDrmncyController | Oracle | ADM.TCUSTOMER_DIV, ADM.TCUSTETC, ADM.TCUSTORDERSUM, ADM.TSEGCUST, ADM.TSEGCODE, ADM.TRCVPATH, ADM.TOBAGREE, ADM.TPERINFO, ADM.TVOC, ADM.TSRPROC |
| /custmng/custDrmncy/resetDrmncyTranSplitCustCiInf | 휴면 이관 분리 고객 CI 정보를 null로 수정한다. | CustDrmncyController | Oracle | ADM.TCUSTOMER_DIV |
| /custmng/custMerge/retrieveMergeCust | 병합 고객을 조회한다. ( 병합 고객 조회 팝업 ) | CustMergeController | Oracle | ADM.TCUSTOMER, ADM.TDUPCUST |
| /custmng/custMerge/retrieveDupGrpCrteCust | 중복그룹생성 고객정보를 조회한다. | CustMergeController | Oracle | ADM.TDUPCUST, ADM.TCUSTOMER |
| /custmng/custMerge/createDupGrp | 고객의 중복그룹을 생성한다. | CustMergeController | Oracle | ADM.TDUPCUST |
| /custmng/custMerge/retrieveCustMergeProc | 중복 고객 목록을 조회한다. | CustMergeController | Oracle | ADM.TDUPCUST, ADM.TCUSTOMER |
| /custmng/custMerge/retrieveDupCustLst | 중복 고객 정보 목록을 조회한다. | CustMergeController | Oracle | ADM.TDUPCUST, ADM.TCUSTOMER, ADM.TCUSTORDERSUM |
| /custmng/custMerge/modifyCustMergeNoAllDup | 고객병합정보를 전체중복아님으로 수정한다. (고객병합처리화면, 전체중복아님버튼 클릭 ) | CustMergeController | Oracle | ADM.TDUPCUST |
| /custmng/custMerge/saveCustMerge | 고객병합을 저장한다. | CustMergeController | Oracle | ADM.TDUPCUST, ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TCUSTMAGREE, MALLOWN.TWEB_ACCOUNT, ADM.TCUSTLASTLOGIN |
| /custmng/custMerge/cancelCustMerge | 고객병합을 취소한다. | CustMergeController | Oracle | ADM.TDUPCUST |
| /custmng/custMerge/retrieveDupGrpCustLst | 중복 그룹 고객 목록을 조회한다. | CustMergeController | Oracle | ADM.TDUPCUST |
| /custmng/custMerge/dupGrpCrteCncl | 중복 그룹 생성을 취소한다. | CustMergeController | Oracle | ADM.TDUPCUST |
| /custmng/custMerge/checkDupCustBnde | 중복고객을 체크한다. ( 중복고객일괄체크처리 배치를 호출 ) | CustMergeController | 존재하지 않음 | 존재하지 않음 |
| /custmng/custMerge/retrieveCustMergeList | 병합고객내역조회 | CustMergeController | Oracle | ADM.TDUPCUST, ADM.TCUSTOMER, ADM.TSEGCUST, ADM.TCUSTADDR |
| /custmng/custMerge/retrieveCustMergeLogList | 고객병합이력목록조회 | CustMergeController | Oracle | ADM.TCUSTMERGELOG, ADM.TCUSTOMER |
| /custmng/custWdraw/retrieveUnprocSrLst | 미처리SR 정보를 조회한다. | CustWdrawController | Oracle | ADM.TSR, ADM.TITEM, ADM.TCODE, ADM.TCUSTOMER, ADM.TASSIGNROLE, ADM.TVOCCODE |
| /custmng/custWdraw/completeUnprocSrProc | 미처리SR 정보 완료 처리한다. | CustWdrawController | Oracle | ADM.TSR |
| /custmng/custWdraw/retrieveUnprocBbsLst | 미처리 게시판 정보를 조회한다. | CustWdrawController | Oracle | ADM.TMALLBOARD, ADM.TCUSTOMER |
| /custmng/custWdraw/completeUnprocBbsProc | 미처리 게시판 처리 완료한다. | CustWdrawController | Oracle | ADM.TMALLBOARD |
| /custmng/custWdraw/retrieveCustWdrawReq | 고객탈퇴요청을 조회한다. | CustWdrawController | Oracle | ADM.TCUSTOMER, ADM.TCUSTWDRAW, ADM.TCUSTPAM, ADM.TBANK, ADM.TCUSTADDR, ADM.TBIZMEMBER |
| /custmng/custWdraw/saveCustWdrawReq | 고객탈퇴요청을 저장한다. | CustWdrawController | Oracle | ADM.TCUSTWDRAW |
| /custmng/custWdraw/saveCustWdrawProc | 고객탈퇴를 처리한다. | CustWdrawController | Oracle | ADM.TCUSTWDRAW |
| /custmng/custWdraw/retrieveCustWdrawProcLst | 고객 탈퇴 처리 화면에서 조회한다. | CustWdrawController | Oracle | ADM.TCUSTOMER, ADM.TCUSTWDRAW, ADM.TBANK, ADM.TPERINFO |
| /custmng/custWdraw/executeCustBndeWdraw | 일괄로 탈퇴를 처리한다. | CustWdrawController | 존재하지 않음 | 존재하지 않음 |
| /custmng/custWdraw/retrieveCustWdrawDtlInf | 고객 탈퇴 상세 정보를 조회한다. | CustWdrawController | Oracle | ADM.TCUSTOMER, ADM.TCUSTWDRAW, ADM.TCUSTPAM, ADM.TBANK, ADM.TCUSTADDR, ADM.TBIZMEMBER |
| /custmng/custWdraw/custWdrawReqCncl | 고객 탈퇴 요청을 취소한다. | CustWdrawController | Oracle | ADM.TCUSTWDRAW |
| /custmng/custWdraw/cancelCustWdrawProc | 고객 탈퇴 처리를 취소한다. | CustWdrawController | Oracle | ADM.TCUSTWDRAW |
| /custmng/custWdraw/retrieveCustWdrawList | 고객탈퇴 요청,처리,취소 목록을 조회한다. | CustWdrawController | Oracle | ADM.TCUSTWDRAW, ADM.TCUSTOMER |
| /custmng/custWdraw/retrieveCustWdrawLogList | 고객탈퇴이력목록을 조회한다. | CustWdrawController | Oracle | ADM.TCUSTMERGELOG |
| /custmng/custWdraw/retrieveUnprocOrdList | 고객의 미처리건 주문목록을 조회한다. | CustWdrawController | Oracle | ADM.TORDERDTL, ADM.TITEM, ADM.TCODE |
| /custseg/custSeg/cjoneCpnIssueTgtYnChk | cjone 쿠폰발행여부 소켓 통신 체크를 위한 임시 api | CustSegController | 존재하지 않음 | 존재하지 않음 |
| /custseg/custSeg/retrieveCustSegInf | 고객 세그먼트를 조회한다. (고객 상세 등록 화면) | CustSegController | Oracle | ADM.TSEGCUST, ADM.TSEGCODE, ADM.TCHANNEL |
| /custseg/custSeg/retrieveCustSegHistLst | 고객 세그먼트를 조회한다. (고객 상세 등록 화면) | CustSegController | Oracle | ADM.TSEGCUSTHIST, ADM.TSEGCODE, ADM.TCHANNEL |
| /custseg/custSeg/saveCustSegMain | 고객 세그먼트를 등록, 수정한다. ( 세그먼트등록, 제외 처리 화면 ) | CustSegController | Oracle | ADM.TSEGCUST |
| /custseg/custSeg/saveCustSegMainAsCust | 고객 세그먼트를 등록, 수정한다. ( 세그먼트등록, 제외 처리 화면 ) | CustSegController | Oracle | ADM.TSEGCUST |
| /custseg/custSeg/retrieveCustSegRegExc | 고객 세그먼트 등록, 제외 목록을 조회한다. ( 세그먼트 등록/제외 처리 화면 ) | CustSegController | Oracle | ADM.TSEGCUST, ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TSEGCODE |
| /custseg/custSeg/retrieveCustSegRegSttus | 고객 세그먼트 등록 현황을 조회한다. | CustSegController | Oracle | ADM.TSEGCODE, ADM.TCHANNEL, ADM.TDEPT, ADM.TPERINFO, ADM.TSEGMSUM |
| /custseg/custSeg/retrieveCustSegRsltSttus | . | CustSegController | Oracle | ADM.TSEGCODE, ADM.TSEGMSUM, ADM.TSCCUSTMSUM, ADM.TDEPT, ADM.TCHANNEL |
| /custseg/custSeg/retrieveCustSegOrdDtl | 고객의 세그먼트 주문 상세 내역을 조회한다. | CustSegController | Oracle | ADM.TORDERDTL, ADM.TORDERITEM, ADM.TITEM, ADM.TCJCARDDC_MEMBER, ADM.TCARDIMDDCCUSTDTL, ADM.TCHANNEL |
| /custseg/custSeg/retrieveCustSegCpnDown | 고객 등급 쿠폰 다운로드 내역을 저장한다. | CustSegController | Oracle | ADM.TCBCODE, ADM.TSEGCUST, ADM.TCUSTOMER |
| /custseg/custSeg/saveCustSegCd | 고객 세그먼트 코드 변경 시 저장한다. | CustSegController | Oracle | ADM.TSEGCUST |
| /custseg/custSeg/retrieveVipDiscount | custNo,selectKeyMonth / VIP 즉시할인 혜택 조회 api | CustSegController | Oracle | ADM.TGRADEDCGIVE, ADM.TOFFER |
| /custseg/custSeg/retrieveOrdVipDiscount | custNo,selectKeyMonth / 주문상세 조회 api | CustSegController | Oracle | ADM.TGRADEDCUSE, ADM.TORDERITEM, ADM.TORDERDTL, ADM.TITEM, ADM.TITEMCHNL, ADM.TCJCARDDC_MEMBER, ADM.TCARDIMDDCCUSTDTL, ADM.TGRADEDCLINK, ADM.TGRADEDCGIVE |
| /custseg/custSeg/updateVipDiscount | VIP즉시할인 한도내역 수정 | CustSegController | Oracle | ADM.TGRADEDCGIVE |
| /custseg/mnbyUnfairCust/saveMnbyUnfairCustLst | 월별부당고객을 등록,수정한다. | MnbyUnfairCustController | 존재하지 않음 | 존재하지 않음 |
| /custseg/mnbyUnfairCust/retrieveMnbyUnfairCustLst | 월별부당고객을 조회한다. | MnbyUnfairCustController | Oracle | ADM.TUNFAIRCUSTMON, ADM.TCUSTOMER, ADM.TCODE |
| /custseg/mnbyUnfairCust/retrieveMnbyUnfairCustSumry | 월별 부당 고객 요약 내역을 조회한다. | MnbyUnfairCustController | Oracle | ADM.TUNFAIRCUSTMON, ADM.TCUSTOMER, ADM.TCODE |
| /custseg/mnbyUnfairCust/retrieveMnbyUnfairCustAllHist | 월별부당고객의 전체내역을 조회한다. | MnbyUnfairCustController | Oracle | ADM.TUNFAIRCUSTMON, ADM.TCUSTOMER, ADM.TORDERITEM, ADM.TITEM, ADM.TORDERDTL |
| /custseg/mnbyUnfairCust/modifyMnbyUnfairCustGrd | 월별부당고객의 등급을 수정한다. | MnbyUnfairCustController | Oracle | ADM.TUNFAIRCUSTMON |
| /custseg/ordLmtCust/retrieveOrdLmtCustAgg | 주문제한고객의 세그먼트별 고객 COUNT, 주문거절 COUNT를 조회한다. | OrdLmtCustController | Oracle | ADM.TSEGCODE, ADM.TORDERREJECTCUST |
| /custseg/ordLmtCust/saveOrdLmtCust | 주문제한고객을 등록, 수정한다. ( 클레임, 주문 요청 ) | OrdLmtCustController | Oracle | ADM.TORDERREJECTCUST, ADM.TSEGCUST, ADM.TCUSTOMER |
| /custseg/ordLmtCust/retrieveOrdLmtCustAsUnfairCustFstRegDt | 주문 제한 고객을 부당 고객 최초 등록 일자 기준으로 조회한다. | OrdLmtCustController | Oracle | ADM.TSEGCUST, ADM.TSEGCUSTHIST, ADM.TCUSTOMER |
| /custseg/ordLmtCust/retrieveOrdLmtCustAsRsnRegDt | 주문 제한 고객을 사유별 등록 일자 기준으로 조회한다. | OrdLmtCustController | Oracle | ADM.TORDERREJECTCUST, ADM.TORDERITEM, ADM.TITEM, ADM.TCUSTOMER, ADM.TSEGCUST, ADM.TSEGCODE |
| /custseg/ordLmtCust/retrieveOrdLmtCustAsOrdLmtRegDt | 주문 제한 고객을 주문 제한 등록 일자 기준으로 조회한다. | OrdLmtCustController | Oracle | ADM.TORDERREJECTCUST, ADM.TORDERITEM, ADM.TITEM, ADM.TCUSTOMER, ADM.TSEGCUST, ADM.TSEGCODE |
| /custseg/ordLmtCust/saveOrdLmtCustMain | 주문 제한 고객 화면에서 저장한다. | OrdLmtCustController | Oracle | ADM.TORDERREJECTCUST, ADM.TSEGCUST, ADM.TCUSTOMER |
| /custseg/ordLmtCust/retrieveOrdLmtCust | 주문제한고객을 조회한다. | OrdLmtCustController | Oracle | ADM.TORDERDTL, ADM.TORDERITEM, ADM.TITEM, ADM.TCUSTOMER, ADM.TSEGCUST, ADM.TSEGCODE |
| /custseg/ordLmtCust/retrieveOrdLmtRsnCd | 주문 제한 사유 코드를 조회한다. | OrdLmtCustController | Oracle | ADM.TCODE, ADM.TSEGCODE |
| /custseg/ptcrlnclnCust/savePtcrInclnCustFileUpload | 특이성향고객파일업로드 내역을 저장한다. | PtcrInclnCustController | Oracle | ADM.TCBCODE, ADM.TSINGCUST |
| /custseg/ptcrlnclnCust/retrievePtcrInclnCustFileUpload | 특이성향고객파일업로드 내역을 조회한다. | PtcrInclnCustController | Oracle | ADM.TSINGCUST, ADM.TCBCODE |
| /custseg/ptcrlnclnCust/retrievePtcrInclnCustList | 특이성향고객리스트를 조회한다. | PtcrInclnCustController | Oracle | ADM.TSINGCUST |
| /custseg/ptcrlnclnCust/savePtcrInclnCustList | 특이성향고객리스트 내역을 저장한다. | PtcrInclnCustController | Oracle | ADM.TSINGCUST |
| /custseg/ptcrlnclnCust/retrievePtcrInclnCustFulrspYn | 해당 고객번호가 특성(전담)고객인지 판단 | PtcrInclnCustController | Oracle | ADM.TSINGCUST |
| /custseg/segCd/retrieveSegCdDtl | 세그먼트 코드 상세를 조회한다. | SegCdController | Oracle | ADM.TSEGCODE |
| /custseg/segCd/saveSegMgrpCd | 세그먼트중분류코드를 저장한다. | SegCdController | Oracle | ADM.TSEGCODE |
| /custseg/segCd/saveSegCd | 세그먼트코드를 저장한다. | SegCdController | Oracle | ADM.TSEGCODE |
| /custseg/segCd/retrieveSegCdLst | 세그먼트코드를 조회한다. (세그먼트중분류 값은 필수) | SegCdController | Oracle | ADM.TSEGCODE |
| /custseg/segCd/retrieveSegMgrp | 세그먼트중분류를 조회한다. Input인자값이 없으면 전체조회한다. | SegCdController | Oracle | ADM.TSEGCODE |
| /custseg/segCd/retrieveSegMngDept | 존재하지 않음 | SegCdController | Oracle | ADM.TDEPT |
| /custseg/segCd/retrieveSegDivideTermNm | 존재하지 않음 | SegCdController | Oracle | ADM.TCODE |
| /custsys/batchMonit/retrieveCustBatchJobSttusList | 고객 배치 JOB 실행 현황 목록을 조회한다. | BatchMonitController | Oracle | ADM.BATCH_JOB_EXECUTION, ADM.BATCH_JOB_INSTANCE, ADM.TBATCH_JOB, ADM.TCODE, ADM.BATCH_JOB_EXECUTION_PARAMS |
| /custsys/batchMonit/retrieveCustBatchJobExecInfoList | 고객배치 JOB 실행목록를 조회한다. | BatchMonitController | Oracle | ADM.BATCH_JOB_EXECUTION, ADM.BATCH_JOB_INSTANCE, ADM.TBATCH_JOB, ADM.TCODE, ADM.BATCH_JOB_EXECUTION_PARAMS |
| /custsys/batchMonit/retrieveCustBatchStepExecInfoList | 고객배치 STEP 실행목록를 조회한다. | BatchMonitController | Oracle | ADM.BATCH_STEP_EXECUTION, ADM.BATCH_JOB_EXECUTION, ADM.BATCH_JOB_INSTANCE |
| /custsys/batchMonit/retrieveCustBatchJobList | 고객배치목록을 조회한다. | BatchMonitController | Oracle | ADM.TBATCH_JOB, ADM.TCODE |
| /custsys/batchMonit/saveCustBatchJobList | 고객배치목록을 저장(수정, 삭제) 한다 | BatchMonitController | Oracle | ADM.TBATCH_JOB |
| /custsys/batchMonit/saveCustBatchJob | 고객배치를 저장(생성, 수정)한다 | BatchMonitController | Oracle | ADM.TBATCH_JOB |
| /custsys/batchMonit/checkAvailableBatchName | 배치명사용가능여부체크. | BatchMonitController | Oracle | ADM.TBATCH_JOB |
| /custsys/custCode/retrieveRcvpathCdByLvlList | 가입경로코드 코드목록조회 | CustCodeController | Oracle | ADM.TRCVPATH |
| /custsys/custCode/retrieveRcvpathCdList | 가입경로코드목록조회 | CustCodeController | Oracle | ADM.TRCVPATH |
| /custsys/custCode/saveRcvpathCdList | 가입경로코드저장 | CustCodeController | Oracle | ADM.TRCVPATH |
| /custsys/custCode/retrieveMsgTmplList | 메시지템플릿목록조회 | CustCodeController | Oracle | ADM.TCHR_MSG_TMPL, ADM.TKAKAOTALK_TEMPLATE, ADM.TCODE |
| /custsys/custStats/retrieveCustdilyaggList | 회원서비스일간집계 정보 조회 | CustStatsController | Oracle | ADM.TCUSTDILYAGG, ADM.TCODE |
| /custsys/custStats/retrieveWebAcctConvSttusList | TV고객 CJ온스타일 전환 현황조회한다. | CustStatsController | Oracle | MALLOWN.TWEB_ACCOUNT |
| /custsys/custStats/retrieveWebAcctConvDilySttusList | 웹계정 전환 일간 현황 조회한다. | CustStatsController | Oracle | MALLOWN.TWEB_ACCOUNT |
| /custsys/custStats/retrieveLoginRealtmSttusList | 로그인실시간현황목록을 조회한다 | CustStatsController | Oracle | MALLOWN.TWEB_LOGIN_LOG |
| /custsys/custStats/retrieveRegisterRealtmSttusList | 회원가입실시간현황목록을 조회한다 | CustStatsController | Oracle | MALLOWN.TWEB_ACCOUNT |
| /custsys/custStats/retrieveCustCntdilyaggList | 회원현황집계목록 조회 | CustStatsController | Oracle | ADM.TCUSTDILYAGG |
| /custsys/custStats/retrieveCustSexAgrgdCntdilyaggList | 고객성별/연령대집계목록 조회 | CustStatsController | Oracle | ADM.TCUSTDILYAGG |
| /custsys/custStats/retrieveLoginRealtmSttusAgg | 실시간로그인정보 집계 조회 | CustStatsController | Oracle | MALLOWN.TWEB_LOGIN_LOG |
| /custsys/custStats/retrieveCustRealtmSttusAgg | 실시간고객정보 집계 조회 | CustStatsController | Oracle | ADM.TCUSTDILYAGG |
| /empmng/empdc/retrieveEmpDc | 임직원 선할인 한도를 조회한다. | EmployeeDiscountController | 존재하지 않음 | 존재하지 않음 |
| /empmng/empdc/retrieveEmpDcList | 임직원 선할인 한도를 조회한다. | EmployeeDiscountController | 존재하지 않음 | 존재하지 않음 |
| /empmng/empdc/retrieveEmpDcIncList | 임직원 할인율 등록 목록 조회 | EmployeeDiscountController | Oracle | ADM.TPAY_USE_INCLUDE, ADM.TITEM, ADM.TITEMKINDS, ADM.TVEN, ADM.TBRAND, ADM.TPERINFO |
| /empmng/empdc/retrieveEmpDcIncExcelList | 임직원 할인율 등록 목록 조회 | EmployeeDiscountController | Oracle | ADM.TPAY_USE_INCLUDE, ADM.TITEM, ADM.TITEMKINDS, ADM.TVEN, ADM.TBRAND, ADM.TPERINFO |
| /empmng/empdc/saveEmpDcInc | 임직원 할인율 등록 저장 | EmployeeDiscountController | Oracle | ADM.TPAY_USE_INCLUDE, ADM.TPAY_USE_INCLUDE_LOG |
| /empmng/empdc/retrieveEmpDcIncHistList | 임직원 할인율 등록 목록 조회 | EmployeeDiscountController | 존재하지 않음 | 존재하지 않음 |
| /Intgmem/IntgMemRecv/IMAPI | CJONE에서 직접 호출되는 메인 API | IntgMemRecvController | 존재하지 않음 | 존재하지 않음 |
| /Intgmem/IntgMemRecv/trans | 로컬회원이 CJONE통합회원으로 전환 등록한다. | IntgMemRecvController | 존재하지 않음 | 존재하지 않음 |
| /Intgmem/IntgMemRecv/transCancel | CJONE 통합회원이 통합회원 전환이후 탈퇴하였을 경우 다시 로컬회원으로 될 수 있도록 알려준다. | IntgMemRecvController | Oracle | ADM.TCUSTTRANS |
| /Intgmem/IntgMemRecv/isLocal | CJONE 통합회원의 로컬회원을 조회한다. | IntgMemRecvController | 존재하지 않음 | 존재하지 않음 |
| /Intgmem/IntgMemRecv/checkIdExist | CJONE통합회원의 로컬회원의 존재 여부를 체크한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /Intgmem/IntgMemRecv/register | CJONE통합회원을 등록한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_OM, ADM.TCUSTADDR, ADM.TCUSTMAGREE, ADM.TCUSTAGRHIST, ADM.TCUSTTRANS |
| /Intgmem/IntgMemRecv/updatePw | CJONE통합회원 비밀번호를 수정한다. | IntgMemRecvController | Oracle | MALLOWN.TWEB_ACCOUNT, MALLOWN.TPWD_CHG_HIST |
| /Intgmem/IntgMemRecv/update | CJONE통합회원정보를 수정한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TCUSTMAGREE, ADM.TCUSTOMER_OM |
| /Intgmem/IntgMemRecv/disAgree | 참여사 통합회원 약관 철회 한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER_OM_AGR |
| /Intgmem/IntgMemRecv/getUser | CJONE 통합회원의 정보요청을 조회한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER_OM, ADM.TCUSTOMER, ADM.TCUSTADDR |
| /Intgmem/IntgMemRecv/updatePersonal | CJONE 통합회원의 아이디, 이름, 주민번호, IPIN_CI정보를 수정한다.(통합회원아이디이름주민번호IPINCI수정) | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTADDR, ADM.TCUSTOMER_OM |
| /Intgmem/IntgMemRecv/checkDrop | CJONE통합회원의 탈퇴가능여부를 식별한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /Intgmem/IntgMemRecv/recoverDrop | CJONE탈퇴신청복구를 등록한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /Intgmem/IntgMemRecv/requestDrop | 통합회원탈퇴요청을 처리한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /Intgmem/IntgMemRecv/drop | CJONE통합회원탈퇴 확정 처리 한다. | IntgMemRecvController | Oracle | ADM.TCUSTOMER, ADM.TCUSTOMER_DIV |
| /Intgmem/IntgMemRecv/disAgreeDormant | CJONE통합회원약관을 철회한다. | IntgMemRecvController | 존재하지 않음 | 존재하지 않음 |
| /point/legacy/earn-criteria | 고객적립금을 지급 기준으로 조회한다. | PointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/retrieveTvPointList | 고객방송상품지원금 조회 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/giveCancelTvPointList | 고객방송상품지원금 지급취소 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/retrieveExceptTvPointList | 방송상품지원금 예외리스트 조회 | TvPointController | Oracle | ADM.TPAY_USE_EXCEPT, ADM.TITEM, ADM.TITEMKINDS, ADM.TBRAND, ADM.TMD |
| /point/tvpoint/registerExceptTvPointList | 방송상품지원금 예외 등록 or 수정 | TvPointController | Oracle | ADM.TPAY_USE_EXCEPT |
| /point/tvpoint/deleteExceptTvPointList | 방송상품지원금 예외항목 삭제 | TvPointController | Oracle | ADM.TPAY_USE_EXCEPT |
| /point/tvpoint/retrieveTvPoint | 고객방송상품지원금 조회 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/giveCancelTvPointAll | 고객방송상품지원금 일괄 지급취소 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/modifyTvPointExpireDate | 고객방송상품지원금 만료일자 변경 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/retrieveIncludeTvPointList | 방송상품지원금 포함리스트 조회 | TvPointController | Oracle | ADM.TPAY_USE_INCLUDE, ADM.TITEM, ADM.TITEMKINDS, ADM.TVEN, ADM.TBRAND |
| /point/tvpoint/registerIncludeTvPointList | 방송상품지원금 포함 등록 or 수정 | TvPointController | Oracle | ADM.TPAY_USE_INCLUDE |
| /point/tvpoint/deleteIncludeTvPointList | 방송상품지원금 포함항목 삭제 | TvPointController | Oracle | ADM.TPAY_USE_INCLUDE |
| /point/tvpoint/retrieveIncludeTvPointLogList | 방송상품지원금 포함로그리스트 조회 | TvPointController | Oracle | ADM.TPAY_USE_INCLUDE_LOG, ADM.TPERINFO |
| /point/tvpoint/cancelExpired | 방송상품지원금 만료 취소 처리 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /point/tvpoint/retrieveCancelExpireHistory | 방송상품지원금 만료이력조회 | TvPointController | 존재하지 않음 | 존재하지 않음 |
| /webacct/blockMem/retrieveBlockMemberInfList | 차단회원정보목록조회한다. | BlockMemController | Oracle | MALLOWN.TBLOCK_MEMBER, ADM.TCODE |
| /webacct/blockMem/checkBlockMemberList | 차단회원유효성체크한다. | BlockMemController | 존재하지 않음 | 존재하지 않음 |
| /webacct/blockMem/registerBlockMemberList | 차단회원목록등록한다. | BlockMemController | Oracle | MALLOWN.TBLOCK_MEMBER |
| /webacct/blockMem/registerBlockMember | 차단회원등록한다. | BlockMemController | Oracle | MALLOWN.TBLOCK_MEMBER |
| /webacct/blockMem/saveBlockMemberListBlockRls | 차단회원 차단해제목록을 저장한다. | BlockMemController | Oracle | MALLOWN.TBLOCK_MEMBER |
| /webacct/blockMem/retrieveBlockingListInfList | 차단목록정보 목록을 조회한다. | BlockMemController | Oracle | MALLOWN.TBLOCKING_LIST, ADM.TCODE |
| /webacct/blockMem/saveBlockingListListBlockRls | 차단목록 차단해제목록을 저장한다. | BlockMemController | Oracle | MALLOWN.TBLOCKING_LIST |
| /webacct/blockMem/registerBlockIdList | 차단회원목록등록한다. | BlockMemController | Oracle | MALLOWN.TBLOCK_MEMBER |
| /webacct/fido/retrieveFIDOInfoList | 온트러스트 생체인증(FIDO)연동 관리 화면에서 생체인증(FIDO)연동 정보를 조회한다 | FIDORegController | Oracle | MALLOWN.TCUST_FIDO_AUTH_INFO, ADM.TCUSTOMER |
| /webacct/fido/retrieveFIDOHistList | 온트러스트 생체인증(FIDO)연동 관리 화면에서 생체인증(FIDO)연동 이력 정보를 조회한다 | FIDORegController | Oracle | MALLOWN.TCUST_FIDO_AUTH_INFO_LOG, ADM.TCUSTOMER, ADM.TUSER_AGENT |
| /webacct/fido/deregFIDOHistInfoList | 온트러스트 생체인증(FIDO)연동 관리 화면에서 생체인증(FIDO)연동을 해제한다. | FIDORegController | Oracle | MALLOWN.TCUST_FIDO_AUTH_INFO_LOG |
| /webacct/thirdparty/retrieveThirdpartyLoginInfoList | 온트러스트 제3자로그인관리 화면에서 제3자로그인 연동정보를 조회한다 | ThirdpartyLoginController | Oracle | MALLOWN.THIRDPARTY_LOGIN_INFO, ADM.TCUSTOMER |
| /webacct/thirdparty/retrieveThirdpartyLoginHistList | 온트러스트 제3자로그인관리 화면에서 제3자로그인 연동 이력을 조회한다 | ThirdpartyLoginController | Oracle | MALLOWN.THIRDPARTY_LOGIN_HIST |
| /webacct/thirdparty/executeTprtLoginIntlkRemov | 온트러스트 제3자로그인관리 화면에서 제3자로그인 연동을 해제한다 | ThirdpartyLoginController | Oracle | MALLOWN.THIRDPARTY_LOGIN_INFO, MALLOWN.THIRDPARTY_LOGIN_INFO_LOG |
| /webacct/webacct/checkIdAndPwd | 존재하지 않음 | WebAccountController | Oracle | MALLOWN.TWEB_ACCOUNT, ADM.TCUSTOMER, ADM.TCUSTLASTLOGIN, MALLOWN.TPWD_CHG_HIST, ADM.TSEGCUST |