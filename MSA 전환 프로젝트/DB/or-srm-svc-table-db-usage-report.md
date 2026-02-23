# or-srm-svc-table 데이터베이스 사용 내역 보고서

이 문서는 프로젝트의 각 API 엔드포인트가 어떤 서비스, 쿼리를 거쳐 데이터베이스 테이블에 접근하는지 분석한 결과입니다.

## 분석 요약
- 총 분석된 엔드포인트 수: 395개

Loaded cached credentials.
### GET /srmng/srerrmng/retrieveScErrHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.retrieveScErrHist`
  - **Query:** `srerrorJMapper.selectScErrHist`
    - **Tables:** `ADM.TSRERROR`, `ADM.TSR`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TCUSTOMER`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveCoVendorCustInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveCoVendorCustInf`
  - **Query:** `vendorJMapper.selectCoVendorCustInf`
    - **Tables:** `ADM.TB2BPRTNR`, `ADM.TVENADDINFO`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveCustBbsHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveCustBbsHist`
  - **Query:** `mallboardJMapper.selectCustBbsHist`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TORDERDTL`, `ADM.TMALLBOARD_ATT`, `ADM.TITEMCHNL`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TVOC`, `ADM.TMALLBOARDPROC`, `ADM.TSEGCUST`, `ADM.TVENDOR`, `ADM.TPERINFO`, `ADM.TSITE_MALL`, `ADM.TVOCCODE`, `ADM.TBOARDSCASSIGN`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsHistPaging
-   **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsHistPaging`
    -   **Query:** `mallboardJMapper.selectBbsHistTcount`
        -   **Tables:** `ADM.TMALLBOARD`, `ADM.TCUSTOMER`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDCONT`, `ADM.TVOC`, `ADM.TEXT_REQUESTION`, `ADM.TMALLBOARDPROC`, `ADM.TCODE`
    -   **Query:** `mallboardJMapper.selectBbsHistPaging`
        -   **Tables:** `ADM.TMALLBOARD`, `ADM.TPERINFO`, `ADM.TCUSTOMER`, `ADM.TORDERDTL`, `ADM.TSEGCUST`, `ADM.TMALLBOARD_ATT`, `ADM.TITEMCHNL`, `ADM.TSITE_MALL`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDPROC`, `ADM.TCODE`, `ADM.TVOC`, `ADM.TITEMCHNL`, `ADM.TVENDOR`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TEXT_REQUESTION`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsHist`
  - **Query:** `mallboardJMapper.selectBbsHist`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TPERINFO`, `ADM.TCUSTOMER`, `ADM.TORDERDTL`, `ADM.TSEGCUST`, `ADM.TMALLBOARD_ATT`, `ADM.TITEMCHNL`, `ADM.TSITE_MALL`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDPROC`, `ADM.TCODE`, `ADM.TVOC`, `ADM.TVENDOR`, `ADM.TVOCCODE`, `ADM.TEXT_REQUESTION`

Loaded cached credentials.
### POST /vocmng/bbs/excelBbsHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.excelBbsHist`
  - **Query:** `mallboardJMapper.selectBbsHistExcel`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TPERINFO`, `ADM.TCUSTOMER`, `ADM.TORDERDTL`, `ADM.TSEGCUST`, `ADM.TMALLBOARD_ATT`, `ADM.TITEMCHNL`, `ADM.TSITE_MALL`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDPROC`, `ADM.TCODE`, `ADM.TVOC`, `ADM.TVENDOR`, `ADM.TVOCCODE`, `ADM.TEXT_REQUESTION`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsImg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsImg`
  - **Query:** `mallboardJMapper.selectBbsImg`
    - **Tables:** `ADM.TVOC_ATT`, `ADM.TMALLBOARD`, `ADM.TMALLBOARDCONT`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveScSvAuth
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveScSvAuth`
  - **Query:** `assignscrolehistJMapper.selectAssignRolesSummary`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TPERINFO`, `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsAssignRoles
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsAssignRoles`
  - **Query:** `assignscrolehistJMapper.selectAssignRolesSummary`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TPERINFO`, `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveItemTypeAddition
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveItemTypeAddition`
  - **Query:** `mallboardJMapper.selectItemTypeAddition`
    - **Tables:** `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveSegmentCode
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveSegmentCode`
  - **Query:** `mallboardJMapper.selectSegmentCode`
    - **Tables:** `ADM.TSEGCODE`

Loaded cached credentials.
### POST /vocmng/bbs/saveBbsReply
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.saveBbsReply`
  - **Query:** `boardscassignMapper.selectBbsAsgnCnclYn`
    - **Tables:** `ADM.TBOARDSCASSIGN`
  - **Query:** `mallboardprocMapper.insertMallBoardReply`
    - **Tables:** `ADM.TMALLBOARDPROC`
  - **Query:** `mallboardMapper.updateBbsStat`
    - **Tables:** `ADM.TMALLBOARD`, `DUAL`
  - **Query:** `vendorJMapper.selectIsExtPartnerByCustNo`
    - **Tables:** `ADM.TCODE`
  - **Query:** `mallboardprocMapper.updateBbsCnts`
    - **Tables:** `ADM.TMALLBOARDPROC`

Loaded cached credentials.
### GET /vocmng/voc/retrieveVocCustInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocCustInf`
  - **Query:** `customerJMapper.selectCustDtlInf`
    - **Tables:** `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TCUSTOMER_OM`, `ADM.TVENDOR`, `ADM.TDUPCUST`, `ADM.TPERINFO`, `ADM.TCJMEMBER`, `ADM.TGRPCOMP`, `ADM.TRELCUST`, `ADM.TSINGCUST`, `ADM.TVOC`, `ADM.TSRPROC`, `ADM.TCUSTOMER`, `ADM.TCUSTETC`, `ADM.TCUSTORDERSUM`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `ADM.TRCVPATH`, `ADM.TOBAGREE`
  - **Query:** `customerJMapper.selectCustAddrLst`
    - **Tables:** `ADM.TCODE`, `ADM.TCUSTADDR`, `ADM.TCUSTOMER`
  - **Query:** `customerJMapper.selectDupCustAddrLst`
    - **Tables:** `ADM.TDUPCUST`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `customerJMapper.selectCustMktngAgrLst`
    - **Tables:** `ADM.TCODE`, `ADM.TCUSTMAGREE`
  - **Query:** `vocJMapper.selectCustScId`
    - **Tables:** `ADM.TSEGCUST`
  - **Query:** `vocJMapper.selectCustSingluarYn`
    - **Tables:** `ADM.TSINGCUST`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsReplyInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsReplyInf`
  - **Query:** `vocJMapper.selectSrProcessCustomerCls`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TITEM`, `ADM.TPERINFO`, `ADM.TVENPLACE`, `ADM.TCODE`, `ADM.TCHANNEL`
  - **Query:** `orderdtlJMapper.selectOrderProgressItem`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TRESTRICTORDER`, `ADM.TORDER`, `ADM.TORDERETC`, `ADM.TITEMCHNL`, `ADM.TCUSTDCCOUPON`, `ADM.TOFFER`, `ADM.TORDERITEMPROMO`, `ADM.TORDERPRESENT`, `ADM.TITEMADD`, `ADM.TRGLR_PAY_ORD_SCHD`, `ADM.TCBCODE`, `MALLOWN.TORDER_VEN_KEY`, `ADM.TCHANNELSALE`, `MALLOWN.TMCOM_PARTNER_CODE`, `MALLOWN.TPARTNERM`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TPRESHIPREQ`, `ADM.TSECONDDELIVPLAN`, `ADM.THOPEDELY`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`, `ADM.THPOINTSAVEAMT_OM`, `ADM.TINAMT`, `ADM.TPAYCOAPPV`, `ADM.TKAKAOPAYAPPV`, `ADM.TSYRUPAPPV`, `ADM.TPAYNOWPGAPPV`, `ADM.TTVPAYAPPV`, `ADM.TORDER_CPN_PRTNR`, `ADM.TREPAYINFO`, `ADM.TORDERCOST`
  - **Query:** `mallboardprocJMapper.selectBbsDtlHist`
    - **Tables:** `ADM.TMALLBOARDPROC`, `ADM.TPERINFO`
  - **Query:** `mallboardJMapper.selectBbsUnitInf`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TVOC`, `ADM.TVOCCODE`, `ADM.TSR`, `ADM.TMALLBOARDPROC`, `ADM.TMALLBOARDCONT`, `ADM.TPERINFO`
  - **Query:** `ansbankJMapper.selectBbsGrtwrd`
    - **Tables:** `ADM.TANSBANK`, `DUAL`
  - **Query:** `vocJMapper.selectItemRcptWarnInfo`
    - **Tables:** `ADM.TDELIVCST`, `ADM.TITEM`, `ADM.TITEMADD`, `ADM.TITEMPOINT`
  - **Query:** `mallboardJMapper.selectCxrTgtItemInf`
    - **Tables:** `ADM.TMALLCLAIMITEMSTS`, `ADM.TMALLCLAIMCODE`
  - **Query:** `vendorJMapper.selectIsExtPartnerByCustNo`
    - **Tables:** `ADM.TCODE`
  - **Query:** `vocJMapper.selectCustBbsVocHist`
    - **Tables:** `ADM.TVOC`, `ADM.TCONTACT`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TCODE`, `ADM.TSR`
  - **Query:** `srJMapper.selectCustSrHist`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TCHANNEL`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TVENDOR`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `mallboardJMapper.selectMallBbsReplyTmpl`
    - **Tables:** `ADM.TBOARDREPLYTEMPLATE`

Loaded cached credentials.
### POST /vocmng/bbs/modifyBbsDelYn
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.modifyBbsDelYn`
  - **Query:** `mallboardMapper.updateBbsUseYn`
    - **Tables:** `ADM.TMALLBOARD`

Loaded cached credentials.
### GET /vocmng/bbs/assignOneBbsSc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.assignOneBbsSc`
  - **Query:** `mallboardJMapper.selectMallBoardScAssign`
    - **Tables:** `ADM.TMALLBOARD`

Loaded cached credentials.
### POST /vocmng/bbs/assignBbsSc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.assignBbsSc`
  - **Query:** `mallboardJMapper.selectBbsNoAsgnHist`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TORDERDTL`, `ADM.TITEMVEN`, `ADM.TITEM`, `ADM.TSEGCUST`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TMALLBOARDCONT`, `ADM.TMALLBOARDPROC`, `ADM.TCBCODE`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsDtlHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsDtlHist`
  - **Query:** `mallboardprocJMapper.selectBbsDtlHist`
    - **Tables:** `ADM.TMALLBOARDPROC`, `ADM.TPERINFO`
  - **Query:** `mallboardJMapper.selectBbsRequstRead`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TMALLBOARDPROC`, `ADM.TMALLBOARDCONT`, `ADM.TVOC`
  - **Query:** `vocJMapper.selectBbsVocHist`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TITEM`
  - **Query:** `mallboardJMapper.selectBbsImg`
    - **Tables:** `ADM.TVOC_ATT`, `ADM.TMALLBOARD`, `ADM.TMALLBOARDCONT`


Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsAutoAsgnBatchPfrm
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsAutoAsgnBatchPfrm`
  - **Query:** `mallboardJMapper.selectBbsAutoAsgnBatchPfrm`
    - **Tables:** `ADM.TCBCODE`

Loaded cached credentials.
### GET /vocmng/bbs/modifyBbsStat
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.modifyBbsStat`
  - **Query:** `mallboardMapper.updateBbsStat`
    - **Tables:** `ADM.TMALLBOARD`, `DUAL`

Loaded cached credentials.
### GET /vocmng/bbs/assignBbsSelf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.assignBbsSelf`
  - **Query:** `mallboardJMapper.selectBbsAutoAsgnBatchPfrm`
    - **Tables:** `ADM.TCBCODE`
  - **Query:** `mallboardJMapper.selectBoardAssignTarget`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TCODE`, `ADM.TMALLBOARDPROC`

Loaded cached credentials.
### POST /vocmng/bbs/changeBbsProcPreplnSC
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.changeBbsProcPreplnSC`
  - **Query:** `boardscassignMapper.updateBbsAsgnRelis`
    - **Tables:** `ADM.TBOARDSCASSIGN`
  - **Query:** `mallboardMapper.updateBbsAsgnInf`
    - **Tables:** `ADM.TMALLBOARD`
  - **Query:** `boardscassignMapper.insertBbsSCAsgn`
    - **Tables:** `ADM.TBOARDSCASSIGN`, `ADM.TASSIGNSCROLE`, `ADM.TASSIGNROLE`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsAsgnTgtHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsAsgnTgtHist`
  - **Query:** `mallboardJMapper.selectBbsAsgnTgtHist`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TITEMVEN`, `ADM.TSEGCUST`, `ADM.TSITE_MALL`, `ADM.TMALLBOARDPROC`, `ADM.TPERINFO`, `ADM.TCBCODE`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveVocCustOrd
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocCustOrd`
  - **Query:** `orderdtlJMapper.selectVocWaitOrd`
    - **Tables:** `ADM.TCOUNORDER`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`
  - **Query:** `orderdtlJMapper.selectVocOrdDelivCd`
    - **Tables:** `ADM.TORDERDTL`, `DUAL`
  - **Query:** `orderdtlJMapper.selectVocCustOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`, `ADM.TLOGIHDAY`
  - **Query:** `orderdtlJMapper.selectVocOrdDeliv`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TRECEIVER_FB`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCUSTOMER`, `ADM.TADDR_LNM`
  - **Query:** `orderdtlJMapper.selectUnshipCnclDefer`
    - **Tables:** `ADM.TORDERCANCELTARGET`, `ADM.TORDERCANCELDEFER`, `ADM.TSTOCKPENALTY`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERITEM`
  - **Query:** `orderdtlJMapper.selectUnshipCnclDeferYn`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERCANCELDEFER`
  - **Query:** `orderdtlJMapper.selectVocUnitInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMKINDS`
  - **Query:** `orderdtlJMapper.selectVocItemInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMKINDS`
  - **Query:** `orderdtlJMapper.selectVocItemGrpInfo`
    - **Tables:** `DUAL`, `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsChrgAllSC
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.retrieveBbsChrgAllSC`
  - **Query:** `scworkpartJMapper.selectBbsChrgAllSC`
    - **Tables:** `ADM.TCCWORKPART`, `ADM.TCOMPANY`, `ADM.TCODE`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsVocSrInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsVocSrInfo`
  - **Query:** `vocJMapper.selectSrProcessCustomerCls`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TITEM`, `ADM.TPERINFO`, `ADM.TVENPLACE`, `ADM.TCODE`, `ADM.TCHANNEL`
  - **Query:** `mallboardprocJMapper.selectBbsDtlHist`
    - **Tables:** `ADM.TMALLBOARDPROC`, `ADM.TPERINFO`
  - **Query:** `mallboardJMapper.selectBbsUnitInf`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TVOC`, `ADM.TVOCCODE`, `ADM.TSR`, `ADM.TMALLBOARDPROC`, `ADM.TMALLBOARDCONT`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsBizwrkProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsBizwrkProcSttus`
  - **Query:** `mallboardMapper.selectBbsBizwrkProcSttus`
    - **Tables:** `ADM.TSCWORKPART`, `ADM.TMALLBOARD`, `ADM.TBOARDSCASSIGN`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveOfcwrkTakBbsSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveOfcwrkTakBbsSttus`
  - **Query:** `mallboardMapper.selectOfcwrkTakBbsSttus`
    - **Tables:** `ADM.TSCWORKPART`, `ADM.TMALLBOARD`, `ADM.TBOARDSCASSIGN`, `ADM.TPERINFO`, `ADM.TVOC`, `ADM.TSR`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveOfcwrkTakBbsSttusDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveOfcwrkTakBbsSttusDtl`
  - **Query:** `mallboardJMapper.selectOfcwrkTakBbsSttusDtl`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TPERINFO`, `ADM.TCUSTOMER`, `ADM.TORDERDTL`, `ADM.TSEGCUST`, `ADM.TMALLBOARD_ATT`, `ADM.TITEMCHNL`, `ADM.TSITE_MALL`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDPROC`, `ADM.TVOC`, `ADM.TVOCCODE`

Loaded cached credentials.
### POST /vocmng/bbs/saveBbsAutoAsgnStop
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.saveBbsAutoAsgnStop`
  - **Query:** `boardautoasgnhistMapper.updateBbsAutoAsgnStop`
    - **Tables:** `ADM.TBOARDAUTOASGNHIST`
  - **Query:** `scworkpartMapper.updateBbsAutoAsgnStopProc`
    - **Tables:** `ADM.TSCWORKPART`
  - **Query:** `mallboardMapper.selectBbsAsgnCnclTgt`
    - **Tables:** `ADM.TMALLBOARD`
  - **Query:** `mallboardMapper.updateBbsAsgnInf`
    - **Tables:** `ADM.TMALLBOARD`
  - **Query:** `boardscassignMapper.updateBbsAsgnRelis`
    - **Tables:** `ADM.TBOARDSCASSIGN`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsMngSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsMngSttus`
  - **Query:** `mallboardMapper.selectBbscRsltSttus`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TBOARDSCASSIGN`, `DUAL`
  - **Query:** `mallboardJMapper.selectBbscProcSkill`
    - **Tables:** `ADM.TCCWORKPART`, `ADM.TCODE`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TMALLBOARD`, `ADM.TBOARDSCASSIGN`, `ADM.TVOC`, `ADM.TSR`, `ADM.TCBCODE`
  - **Query:** `boardautoasgnhistMapper.selectAsgnStopRsn`
    - **Tables:** `ADM.TBOARDAUTOASGNHIST`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /vocmng/bbs/saveScBbsSkill
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.saveScBbsSkill`
  - **Query:** `boardautoasgnhistMapper.insertBbsAutoAsgnHist`
    - **Tables:** `ADM.TBOARDAUTOASGNHIST`
  - **Query:** `vocJMapper.selectCbCode`
    - **Tables:** `ADM.TCBCODE`

Loaded cached credentials.
### POST /vocmng/bbs/saveBbsAutoAsgnStrEnd
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.saveBbsAutoAsgnStrEnd`
  - **Query:** `scworkpartMapper.updateBbsAutoAsgnYn`
    - **Tables:** `ADM.TSCWORKPART`
  - **Query:** `boardautoasgnhistMapper.updateBbsAutoAsgnHist`
    - **Tables:** `ADM.TBOARDAUTOASGNHIST`
  - **Query:** `boardautoasgnhistMapper.insertBbsAutoAsgnHist`
    - **Tables:** `ADM.TBOARDAUTOASGNHIST`
  - **Query:** `scworkpartMapper.selectBbsAutoAsgnYn`
    - **Tables:** `ADM.TSCWORKPART`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveBbsAutoAsgnYn
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveBbsAutoAsgnYn`
  - **Query:** `scworkpartMapper.selectBbsAutoAsgnYn`
    - **Tables:** `ADM.TSCWORKPART`, `DUAL`

Loaded cached credentials.
### GET /vocmng/voc/retrieveVocRecptControlInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocRecptControlInfo`
  - **Query:** `vocJMapper.selectVocRecptControlInfo`
    - **Tables:** `ADM.TSRASSIGNBASE`, `ADM.TASSIGNROLE`, `ADM.TORDERDTL`, `ADM.TCODE`

Loaded cached credentials.
### POST /vocmng/bbs/modifyExtExchBbsQnaItemCd
-   **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.modifyExtExchBbsQnaItemCd`
    -   **Query:** `mallboardMapper.updateExtExchBbsQnaItemCd`
        -   **Tables:** `ADM.TMALLBOARD`

Loaded cached credentials.
### POST /vocmng/bbs/modifyExtExchBbsSubjectContents
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.modifyExtExchBbsSubjectContents`
  - **Query:** `mallboardMapper.updateExtExchBbsSubject`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TEXTPARTNER_ORDER`
  - **Query:** `mallboardcontMapper.updateExtExchBbsContents`
    - **Tables:** `ADM.TMALLBOARDCONT`

Loaded cached credentials.
### POST /vocmng/bbs/saveExtExchBbsInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.saveExtExchBbsInfo`
  - **Query:** `mallboardMapper.insertExtExchBbs`
    - **Tables:** `ADM.TMALLBOARD`
  - **Query:** `mallboardcontMapper.insertExtExchBbsContent`
    - **Tables:** `ADM.TMALLBOARDCONT`

Loaded cached credentials.
### GET /vocmng/bbs/retrieveSrAssgnHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.retrieveSrAssgnHist`
  - **Query:** `boardscassignMapper.selectSrAssgnHist`
    - **Tables:** `ADM.TBOARDSCASSIGN`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/bbs/baseApiTest
- **Service:** `cj.bts.or.srm.domains.vocmng.service.BbsService.baseApiTest`
  - **Note:** This service method does not appear to call any mapper methods, implying no direct database table access based on the provided analysis.

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrList
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrList`
  - **Query:** `srJMapper.selectSrListByCust`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListBySCRcptusr`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListBySCAsgnusr`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminReceipt`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminProcess`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TASSIGNSCROLEHIST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminSv`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TASSIGNSCROLEHIST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByVendor`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByDirectItem`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrProcList
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrProcList`
  - **Query:** `srJMapper.selectSrListByCust`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TCHANNEL`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TVENDOR`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListBySCRcptusr`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListBySCAsgnusr`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminReceipt`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminProcess`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByAdminSv`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TASSIGNSCROLEHIST`, `ADM.TSCWORKPART`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByVendor`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TVENDOR`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`
  - **Query:** `srJMapper.selectSrListByDirectItem`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSRVENDORREQ`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TSEGCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TSINGCUST`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TDELAYREWARD`


Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrTpModHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrTpModHist`
  - **Query:** `srJMapper.selectSrTpModHist`
    - **Tables:** `ADM.TSR`, `ADM.TITEM`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TSRSCASSIGN`, `ADM.TVOCCODE`, `ADM.TDEPT`, `ADM.TWORKPART`


Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrTpModDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrTpModDtl`
  - **Query:** `srProcMapper.selectSrTpModDtl`
    - **Tables:** `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrChgrModHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrChgrModHist`
  - **Query:** `srJMapper.selectSrChgrModHist`
    - **Tables:** `ADM.TSR`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TVOCCODE`

Loaded cached credentials.
### POST /vocmng/voc/registerVoc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.registerVocSr`
  - **Query:** `orderdtlJMapper.selectVocCustOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`, `ADM.TLOGIHDAY`
  - **Query:** `vocMapper.selectGenerateVocId`
    - **Tables:** `DUAL`
  - **Query:** `vocMapper.insertVoc`
    - **Tables:** `ADM.TVOC`
  - **Query:** `srMapper.selectNextSrSeq`
    - **Tables:** `ADM.TSR`
  - **Query:** `srMapper.insertSr`
    - **Tables:** `ADM.TSR`, `ADM.TASSIGNROLE`
  - **Query:** `srscassignMapper.insertSrSCAsgn`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TVOC`, `ADM.TSRPROC`
  - **Query:** `vocadmdataMapper.insertVocMngClau`
    - **Tables:** `ADM.TVOCADMDATA`
  - **Query:** `sczoneMapper.insertSczone`
    - **Tables:** `ADM.TSCZONE`
  - **Query:** `vocMapper.selectContactVocCount`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrAsgnModInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrAsgnModInf`
  - **Query:** `srscassignMapper.selectScAsgnInf`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TDEPT`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /vocmng/srproc/saveSrProcHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrProcHist`
  - **Query:** `srProcMapper.selectSrProcSeqMaxVal`
    - **Tables:** `ADM.TSRPROC`
  - **Query:** `srProcMapper.selectSrProcMaxProcSeq`
    - **Tables:** `ADM.TSRPROC`
  - **Query:** `srProcMapper.insertSrProc`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSRSCASSIGN`, `ADM.TSR`
  - **Query:** `srMapper.updateSrTcneedYn`
    - **Tables:** `ADM.TSR`
  - **Query:** `srMapper.updateSrProcCdToSr`
    - **Tables:** `ADM.TSR`
  - **Query:** `srProcMapper.updateSrProcCmpltDt`
    - **Tables:** `ADM.TSRPROC`

Loaded cached credentials.
### POST /vocmng/srproc/saveSrProcHistPost
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrProcHist`
  - **Query:** `srProcMapper.selectSrProcSeqMaxVal`
    - **Tables:** `ADM.TSRPROC`
  - **Query:** `srProcMapper.selectSrProcMaxProcSeq`
    - **Tables:** `ADM.TSRPROC`
  - **Query:** `srProcMapper.insertSrProc`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSRSCASSIGN`, `ADM.TSR`
  - **Query:** `srMapper.updateSrTcneedYn`
    - **Tables:** `ADM.TSR`
  - **Query:** `srMapper.updateSrProcCdToSr`
    - **Tables:** `ADM.TSR`
  - **Query:** `srProcMapper.updateSrProcCmpltDt`
    - **Tables:** `ADM.TSRPROC`

Loaded cached credentials.
### POST /vocmng/srproc/saveSrVenCompReqt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrVenCompReqt`
  - **Query:** `srvendorreqMapper.selectSrVendorReqtChk`
    - **Tables:** `ADM.TSRVENDORREQ`
  - **Query:** `srvendorreqMapper.insertSrVendorReq`
    - **Tables:** `ADM.TSRVENDORREQ`
  - **Query:** `srvendorreqMapper.updateSrVendorReq`
    - **Tables:** `ADM.TSRVENDORREQ`
  - **Query:** `srDelyTransMapper.selectGenCtrlKey`
    - **Tables:** `DUAL`
  - **Query:** `srDelyTransMapper.insertDirdeliDataSend`
    - **Tables:** `ADM.TSR_DELY_TRANS`, `ADM.TVOC`, `ADM.TSR`, `ADM.TSRPROC`, `ADM.TORDERDTL`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`, `ADM.TVOCCODE`, `ADM.TITEM`


Loaded cached credentials.
### POST /vocmng/srproc/saveImprtncSrCcmTran
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveImprtncSrCcmTran`
  - **Query:** `srMapper.updateSrProcessCcmsRequest`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveCardCompSrHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveCardCompSrHist`
  - **Query:** `srJMapper.selectCardCompSrHist`
    - **Tables:** `ADM.TSR`, `ADM.TINAMT`, `ADM.TINAMTPROC`, `ADM.TCUSTOMER`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `ADM.TCUSTADDR`, `ADM.TDELAYREWARD`, `ADM.TCARDDTL`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrProcSubInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrProcSubInfo`
  - **Query:** `srProcJMapper.selectVocClsbyMngClauBySr`
    - **Tables:** `ADM.TVOCADMDATA`, `ADM.TADMCODE`, `ADM.TSR`, `ADM.TVOCADMCODE`
  - **Query:** `srProcJMapper.selectSrOrderAddressInformation`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `srProcJMapper.selectSrReceiverAddressInformation`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TCUSTOMER`
  - **Query:** `srProcJMapper.selectSrProcessContentsDetail`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrRcptProcDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrRcptProcDtl`
  - **Query:** `srProcJMapper.selectSrProcessContentsDetail`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveOrderProgressProg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveOrderProgressProg`
  - **Query:** `orderdtlJMapper.selectOrderProgressProg`
    - **Tables:** `ADM.TORDERPROC`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveOrderInAmt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveOrderInAmt`
  - **Query:** `orderdtlJMapper.selectOrderInAmt`
    - **Tables:** `ADM.TINAMT`, `ADM.TBANK`, `ADM.TBIZ`, `ADM.TINAMT_PAR_CNCL`, `ADM.TSMILEPAYAPPV`, `ADM.TSETTLEBANKAPPV`, `ADM.TKAKAOTALKPAYAPPV`, `ADM.TCHAIAPPV`, `ADM.TONECLICKAPPV`, `ADM.TSAMSUNGPAYAPPV`, `ADM.TCARDIMDDCCUSTDTL`, `ADM.TPAYCOINAMT`, `ADM.TKAKAOPAYAPPV`, `ADM.TSYRUPAPPV`, `ADM.TPAYNOWPGAPPV`, `ADM.TTVPAYAPPV`, `ADM.TPAYCOAPPV`

Loaded cached credentials.
### GET /vocmng/voc/registerVocSrProc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.registerVocSrProc`
  - **Query:** `srProcMapper.insertVocSrProc`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveCustDeposit
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveCustDeposit`
  - **Query:** `orderdtlJMapper.selectCustDeposit`
    - **Tables:** `ADM.TPAMREM`

Loaded cached credentials.
### POST /vocmng/srproc/saveSrEmailSend
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrEmailSend`
  - **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.obResultEmailSending`
    - **Tables:** `ADM.TOBCUST`, `ADM.TREWARD`, `ADM.TPERINFO`
  - **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.mailSending`
    - **Tables:** `ADM.TPERINFO`, `ADM.TCBCODE`, `ADM.TOB`, `ADM.TCODE`
  - **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.mailSendingDate`
    - **Tables:** `ADM.TOB`

Loaded cached credentials.
### POST /vocmng/srproc/saveInfBank
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveInfBank`
  - **Query:** `infobankMapper.selectInfBankDup`
    - **Tables:** `ADM.TINFOBANK`

Loaded cached credentials.
### POST /vocmng/srproc/saveInfBankData
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveInfBankData`
  - **Query:** `infobankMapper.selectInsSeq`
    - **Tables:** `ADM.TINFOBANK`
  - **Query:** `infobankMapper.insertInfBank`
    - **Tables:** `ADM.TINFOBANK`
  - **Query:** `infobankMapper.updateInfBank`
    - **Tables:** `ADM.TINFOBANK`
  - **Query:** `infobankCcmsMapper.selectInfBankCcmsDup`
    - **Tables:** `ADM.TINFOBANK_CCMS`
  - **Query:** `infobankCcmsMapper.insertInfBankCcms`
    - **Tables:** `ADM.TINFOBANK_CCMS`, `ADM.TINFOBANK`, `ADM.TCODE`, `ADM.TITEMKINDS`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrTrctrHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrTrctrHist`
  - **Query:** `srJMapper.selectSrTrctrHist`
    - **Tables:** `ADM.TSR`, `ADM.TCUSTOMER`, `ADM.TVOCCODE`, `ADM.TSRVENDORREQ`, `ADM.TDELAYREWARD`
  - **Query:** `srProcMapper.selectModSrHist`
    - **Tables:** `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/srproc/saveChgSrProcId
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveChgSrProcId`
  - **Query:** `srMapper.updateSrProcessIdChange`
    - **Tables:** `ADM.TSR`
  - **Query:** `srscassignMapper.insertSrSCAsgn`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TVOC`, `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveCustScId
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveCustScId`
  - **Query:** `srProcJMapper.selectCustScId`
    - **Tables:** `ADM.TSEGCUST`

Loaded cached credentials.
### POST /vocmng/srproc/saveSrReasonTransfer
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrReasonTransfer`
  - **Query:** `srMapper.selectSrInfo`
    - **Tables:** `ADM.TSR`
  - **Query:** `vocMapper.selectVocInfo`
    - **Tables:** `ADM.TVOC`
  - **Query:** `voccodeMapper.selectVocClsDtl`
    - **Tables:** `ADM.TVOCCODE`
  - **Query:** `srProcMapper.insertSrProc`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSRSCASSIGN`, `ADM.TSR`
  - **Query:** `srMapper.updateSrProcCdToSr`
    - **Tables:** `ADM.TSR`
  - **Query:** `srscassignMapper.insertSrSCAsgn`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TVOC`, `ADM.TSRPROC`
  - **Query:** `srMapper.updateSrAssignInfo`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrBizwrkProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrBizwrkProcSttus`
  - **Query:** `srProcJMapper.selectSrProcSttus`
    - **Tables:** `ADM.TSR`, `ADM.TDELAYREWARD`, `ADM.TVOC`, `DUAL`
  - **Query:** `srscdsumJMapper.selectSrProcrtSttus`
    - **Tables:** `ADM.TSRSCDSUM`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrProcSttus`
  - **Query:** `srProcJMapper.selectSrProcSttusRstTotalCount`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TSCWORKPARTHIST`, `ADM.TSRERROR`, `ADM.TWORKPART`, `ADM.TVOC`
  - **Query:** `srProcJMapper.selectSrProcSttusRstPaging`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TSRERROR`, `ADM.TSCWORKPARTHIST`, `ADM.TWORKPART`, `ADM.TVOC`

Loaded cached credentials.
### GET /vocmng/voc/retrievetVocTelRecordInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrievetVocTelRecordInfo`
  - **Query:** `vocJMapper.selectVocTelRecordInfo`
    - **Tables:** `ADM.TVOC`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TCODE`, `ADM.TCUSTMAGREE`, `ADM.TITEMKINDS`, `ADM.TCHANNEL`, `ADM.TITEM`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveToffcInslReq
-   **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveToffcInslReq`
    -   **Query:** `orderdtlJMapper.selectToffcInslOrd`
        -   **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TCUSTOMER`, `ADM.TORDER`, `ADM.TITEMPRICE`, `ADM.TWBILLDTL`
    -   **Query:** `oshpurchaseMapper.selectToffcInslTgt`
        -   **Tables:** `ADM.TOSHPURCHASE`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /vocmng/srproc/registerToffcInslReq
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.registerToffcInslReq`
  - **Query:** `orderdtlJMapper.selectToffcInslOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TCUSTOMER`, `ADM.TORDER`, `ADM.TITEMPRICE`, `ADM.TWBILLDTL`
  - **Query:** `oshpurchaseMapper.selectToffcInslTgt`
    - **Tables:** `ADM.TOSHPURCHASE`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /vocmng/srproc/saveSrVendorReqRslt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveSrVendorReqRslt`
  - **Query:** `srMapper.updateSrWBNo`
    - **Tables:** `ADM.TSR`
  - **Query:** `srvendorreqMapper.updateSrVendorReqByPartner`
    - **Tables:** `ADM.TSRVENDORREQ`

Loaded cached credentials.
### GET /vocmng/srproc/saveVocSrProcCancel
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.saveVocSrProcCancel`
  - **Query:** `srProcMapper.insertVocSrProcCancel`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TORDERDTL`

Loaded cached credentials.
### GET /vocmng/srproc/save1372Cust
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.save1372Cust`
  - **Query:** `ADM.TOBCUST`
  - **Query:** `ADM.TCHANNEL`

Loaded cached credentials.
### GET /vocmng/srproc/retrieveSrProcStatsSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrProcService.retrieveSrProcStatsSttus`
  - **Query:** `srProcJMapper.selectSrProcStats`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSR`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### POST /asgnmng/srAsgn/saveSrScAssign
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.saveSrScAssign`
  - **Query:** `srMapper.selectSrInfo`
    - **Tables:** `ADM.TSR`
  - **Query:** `srscassignMapper.insertSrSCAsgn`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TVOC`, `ADM.TSRPROC`
  - **Query:** `srMapper.updateSrForScAssign`
    - **Tables:** `ADM.TSR`, `ADM.TASSIGNROLE`

Loaded cached credentials.
### GET /asgnmng/srAsgn/retrieveAsgnTgtSrHist
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.retrieveAsgnTgtSrHist`
  - **Query:** `vocJMapper.selectTCode`
    - **Tables:** `ADM.TCODE`
  - **Query:** `srscassignJMapper.selectChkWorkType`
    - **Tables:** (Tables unknown based on provided data)
  - **Query:** `srJMapper.selectSCAsgnTgtSrHist`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TITEMCHNL`, `ADM.TITEMVEN`, `ADM.TITEMKINDS`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCHANNEL`, `ADM.TVOCCODE`, `ADM.TASSIGNROLE`, `ADM.TPARTNER`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TORDERDTL`, `ADM.TCODE`
  - **Query:** `srJMapper.selectSCAsgnTgtSrHistSvNotAssignBatchMode`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TITEMCHNL`, `ADM.TITEMVEN`, `ADM.TITEMKINDS`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCHANNEL`, `ADM.TVOCCODE`, `ADM.TASSIGNROLE`, `ADM.TPARTNER`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TORDERDTL`, `ADM.TCODE`
  - **Query:** `srJMapper.selectSCAsgnTgtSrHistSvNotAssign`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TITEMCHNL`, `ADM.TITEMVEN`, `ADM.TITEMKINDS`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCHANNEL`, `ADM.TVOCCODE`, `ADM.TASSIGNROLE`, `ADM.TPARTNER`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TORDERDTL`, `ADM.TCODE`
  - **Query:** `srJMapper.selectSCAsgnTgtSrHistSvAssign`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TITEMCHNL`, `ADM.TITEMVEN`, `ADM.TITEMKINDS`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCHANNEL`, `ADM.TVOCCODE`, `ADM.TASSIGNROLE`, `ADM.TPARTNER`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TORDERDTL`, `ADM.TCODE`

Loaded cached credentials.
### GET /asgnmng/srAsgn/retrieveSvScList
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.retrieveSvScList`
  - **Query:** `assignroleJMapper.selectSvScList`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /asgnmng/srAsgn/saveRealTimeAssign
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.saveRealTimeAssign`
  - **Query:** `assignroleMapper.updateAssignRoleAssignFuncYn`
    - **Tables:** `ADM.TASSIGNROLE`
  - **Query:** `assignrolehistMapper.updateAsgnJobfncCdHist`
    - **Tables:** `ADM.TASSIGNROLEHIST`
  - **Query:** `assignrolehistMapper.insertAsgnJobfncCdHist`
    - **Tables:** `ADM.TASSIGNROLEHIST`
  - **Query:** `assignscroleMapper.updateAssignScAssignYn`
    - **Tables:** `ADM.TASSIGNSCROLE`
  - **Query:** `assignscroleMapper.updateAssignScAssignCntClear`
    - **Tables:** `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveUnShippingDeferHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveUnShippingDeferHist`
  - **Query:** `ordercanceldeferJMapper.selectUnShippingDeferHist`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TORDERCANCELDEFER`, `ADM.TPERINFO`, `ADM.TITEM`, `ADM.TCODE`

Loaded cached credentials.
### GET /asgnmng/srAsgn/retrievescOfcwrkTak
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.retrievescOfcwrkTak`
  - **Query:** `scworkpartJMapper.selectScOfcwrkTak`
    - **Tables:** `ADM.TWORKPART`

Loaded cached credentials.
### POST /asgnmng/srAsgnBase/saveSrAsgnBase
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnBaseService.saveSrAsgnBase`
  - **Query:** `srassignbaseMapper.deleteSrAsgnBase`
    - **Tables:** `ADM.TSRASSIGNBASE`
  - **Query:** `srassignbaseMapper.selectSrAsgnBaseDup`
    - **Tables:** `ADM.TSRASSIGNBASE`
  - **Query:** `srassignbaseMapper.insertSrAsgnBase`
    - **Tables:** `ADM.TSRASSIGNBASE`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /asgnmng/srAsgnBase/retrieveSrAsgnBase
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnBaseService.retrieveSrAsgnBase`
  - **Query:** `srassignbaseJMapper.selectSrAsgnBase`
    - **Tables:** `ADM.TSRASSIGNBASE`, `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /asgnmng/srAsgnBase/retrieveAssignRoles
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.AsgnJobfncCdService.selectAssignRole`
  - **Query:** `asgnJobfncCdMapper.selectAssignRole`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TPERINFO`, `ADM.TDEPT`

Loaded cached credentials.
### GET /asgnmng/srAsgnBase/retrieveAsgnJobfncHist
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.AsgnJobfncCdHistService.retrieveAsgnJobfncHist`
  - **Query:** `assignscrolehistJMapper.selectAsgnJobfncHist`
    - **Tables:** `ADM.TASSIGNROLEHIST`, `ADM.TPERINFO`, `ADM.TDEPT`

Loaded cached credentials.
### POST /asgnmng/srAsgnBase/saveAsgnJobfncHist
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnBaseService.saveAsgnJobfncHist`
  - **Query:** `assignrolehistMapper.insertAsgnJobfncCdHist`
    - **Tables:** `ADM.TASSIGNROLEHIST`
  - **Query:** `assignroleMapper.insertSrAsgnJobfnc`
    - **Tables:** `ADM.TASSIGNROLE`
  - **Query:** `assignrolehistMapper.updateAsgnJobfncCdHist`
    - **Tables:** `ADM.TASSIGNROLEHIST`
  - **Query:** `assignroleMapper.deleteSrAsgnJobfnc`
    - **Tables:** `ADM.TASSIGNROLE`

Loaded cached credentials.
### GET /asgnmng/srAsgnBase/retrieveAsgnJobfncbySc
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.AsgnJobfncbySCService.retrieveAsgnJobfncbySc`
  - **Query:** `assignscroleMapper.selectAsgnJobfncbySc`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSCWORKPART`, `ADM.TWORKPART`

Loaded cached credentials.
### POST /asgnmng/srAsgnBase/saveAsgnJobfncbySc
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.AsgnJobfncbySCService.saveAsgnJobfncbySc`
  - **Query:** `assignscroleMapper.deleteAsgnJobfncbySC`
    - **Tables:** `ADM.TASSIGNSCROLE`
  - **Query:** `assignscrolehistMapper.updateAsgnJobfncbySCHist`
    - **Tables:** `ADM.TASSIGNSCROLEHIST`
  - **Query:** `assignscroleMapper.updateAsgnJobfncbySC`
    - **Tables:** `ADM.TASSIGNSCROLE`
  - **Query:** `assignscrolehistMapper.insertAsgnJobfncbySCHist`
    - **Tables:** `ADM.TASSIGNSCROLEHIST`
  - **Query:** `assignscroleMapper.insertAsgnJobfncbySC`
    - **Tables:** `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /asgnmng/srAsgnBase/retrievePsitnCntrOrga
- **Service:** `cj.bts.or.srm.domains.asgnmng.service.SrAsgnService.retrievePsitnCntrOrga`
  - **Query:** `scworkpartJMapper.selectPsitnCntrOrga`
    - **Tables:** `ADM.TWORKPART`, `ADM.TCODE`, `ADM.TSCWORKPART`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveRwrdDtlHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveRwrdDtlHist`
  - **Query:** `rewardJMapper.selectRwrdHist`
    - **Tables:** `ADM.TREWARD`, `ADM.TCODE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSCWORKPART`, `ADM.TVOC`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TOFFER`, `ADM.TCUSTOMER`, `ADM.TWORKPART`, `ADM.TMD`, `ADM.TSTRUCTURE`, `ADM.TCHANNEL`, `ADM.TBANK`, `ADM.TRECEIVER`, `ADM.TITEMKINDS`, `ADM.TACCTCHECK`, `ADM.TOBCUST`, `ADM.TREFUNDACNTAGR`, `ADM.TCUSTPAM`, `ADM.TVENDOR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveUnshipCnclDeferYn
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveUnshipCnclDeferYn`
  - **Query:** `orderdtlJMapper.selectUnshipCnclDeferYn`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERCANCELDEFER`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveRwrdHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveRwrdHist`
  - **Query:** `rewardJMapper.selectRwrdHist`
    - **Tables:** `ADM.TREWARD`, `ADM.TCODE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSCWORKPART`, `ADM.TVOC`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TOFFER`, `ADM.TCUSTOMER`, `ADM.TWORKPART`, `ADM.TMD`, `ADM.TSTRUCTURE`, `ADM.TCHANNEL`, `ADM.TBANK`, `ADM.TRECEIVER`, `ADM.TITEMKINDS`, `ADM.TACCTCHECK`, `ADM.TOBCUST`, `ADM.TREFUNDACNTAGR`, `ADM.TCUSTPAM`, `ADM.TVENDOR`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/saveRewardInfo
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.saveRewardInfo`
  - **Query:** `rewardMapper.insertRwrd`
    - **Tables:** `ADM.TREWARD`


Loaded cached credentials.
### POST /rwrdmng/rwrdmng/saveRwrdHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.saveRwrdHist`
  - **Query:** `rewardMapper.insertRwrd`
    - **Tables:** `ADM.TREWARD`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/retrieveChkPreSave
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveChkPreSave`
  - **Query:** `rewardMapper.selectRwrdProgStat`
    - **Tables:** `ADM.TREWARD`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveStockLackCnclOrdHist
-   **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveStockLackCnclOrdHist`
    -   **Query:** `cancelrewardJMapper.selectStockLackCnclOrdHist_org`
        -   **Tables:** `ADM.TCANCELREWARD`, `ADM.TLOGIHDAY`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TOBCUST`, `ADM.TOB`, `ADM.TORDERDTL`, `ADM.TCJCARDDC_MEMBER`, `ADM.TCUSTOMER`, `ADM.TORDERPROC`, `ADM.TITEMKINDS`, `ADM.TMD`, `ADM.TCODE`, `ADM.TORDERRETURN_RSN`
    -   **Query:** `cancelrewardJMapper.selectStockLackCnclOrdHist`
        -   **Tables:** `ADM.TCANCELREWARD`, `ADM.TLOGIHDAY`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TOBCUST`, `ADM.TOB`, `ADM.TORDERDTL`, `ADM.TCJCARDDC_MEMBER`, `ADM.TCUSTOMER`, `ADM.TORDERPROC`, `ADM.TITEMKINDS`, `ADM.TMD`, `ADM.TCODE`, `ADM.TORDERRETURN_RSN`
    -   **Query:** `cancelrewardJMapper.selectSrCnclRwrdSubInfo`
        -   **Tables:** `ADM.TORDERDTL`, `ADM.TCUSTDCCOUPON`, `ADM.TSR`, `ADM.TCANCELREWARD`, `DUAL`
    -   **Query:** `cancelrewardJMapper.selectCustTel`
        -   **Tables:** `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveCnclRwrdDtlHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveCnclRwrdDtlHist`
  - **Query:** `cancelrewardMapper.selectCnclRwrdDtlHist`
    - **Tables:** `ADM.TCANCELREWARD`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/saveCnclRwrd
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.saveCnclRwrd`
  - **Query:** `cancelrewardMapper.selectCnclRwrdDup`
    - **Tables:** `ADM.TCANCELREWARD`
  - **Query:** `cancelrewardMapper.selectCancelRewardKey`
    - **Tables:** `ADM.TCANCELREWARD`
  - **Query:** `cancelrewardMapper.insertCnclRwrd`
    - **Tables:** `ADM.TCANCELREWARD`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/saveCnclRwrdAutoGiveProc
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.saveCnclRwrdAutoGiveProc`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveStructureInfo
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveStructureInfo`
  - **Query:** `cancelrewardJMapper.selectStructureInfo`
    - **Tables:** `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TMD`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveCcOrgInfo
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveCcOrgInfo`
  - **Query:** `cancelrewardJMapper.selectCcOrgCompInfo`
    - **Tables:** `ADM.TCOMPANY`
  - **Query:** `cancelrewardJMapper.selectCcOrgDeptInfo`
    - **Tables:** `ADM.TDEPT`
  - **Query:** `cancelrewardJMapper.selectCcOrgWorkpartsInfo`
    - **Tables:** `ADM.TWORKPART`
  - **Query:** `cancelrewardJMapper.selectCcOrgScsInfo`
    - **Tables:** `ADM.TSCWORKPART`, `ADM.TSCWORKPARTHIST`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /vocmng/voc/saveUnshipCnclDefer
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.saveUnshipCnclDefer`
  - **Query:** `ordercanceldeferJMapper.insertUnShippingCancelDefer`
    - **Tables:** `ADM.TORDERCANCELDEFER`, `ADM.TORDERDTL`
  - **Query:** `orderdtlJMapper.selectUnshipCnclDefer`
    - **Tables:** `ADM.TORDERCANCELTARGET`, `ADM.TORDERCANCELDEFER`, `ADM.TSTOCKPENALTY`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERITEM`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveVocSrScAssignHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveVocSrScAssignHist`
  - **Query:** `srscassignJMapper.selectVocSrScAssignHist`
    - **Tables:** `ADM.TSRSCASSIGN`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TDEPT`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveScEmpDeptInfo
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveScEmpDeptInfo`
  - **Query:** `scworkpartJMapper.selectScEmpDeptInfo`
    - **Tables:** `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TDEPT`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveDuplicateReward
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveDuplicateReward`
  - **Query:** `rewardMapper.selectDuplicateReward`
    - **Tables:** `ADM.TREWARD`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveRwrdRcptSttus
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveRwrdRcptSttus`
  - **Query:** `assignroleMapper.selectSearchSvYn`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `DUAL`
  - **Query:** `rewardJMapper.selectRwrdRcptSttus`
    - **Tables:** `ADM.TREWARD`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSCWORKPART`, `ADM.TCODE`, `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/preCheckUpladBndeRwrdHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.preCheckUpladBndeRwrdHist`
  - **Query:** `orderdtlJMapper.selectOrdItemInfo`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`
  - **Query:** `rewardJMapper.selectCjomNo`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TCUSTOMER_OM`
  - **Query:** `rewardJMapper.selectPreRegRwrd`
    - **Tables:** `ADM.TREWARD`, `ADM.TVOC`, `ADM.TOBCUST`

Loaded cached credentials.
### POST /rwrdmng/rwrdmng/saveBndeRwrdHist
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.saveBndeRwrdHist`
  - **Query:** `contactMapper.selectGenerateContactNo`
    - **Tables:** `DUAL`, `ADM.Q_CONTACT_NO`, `ADM.Q_CONTACT_NO_MON`, `ADM.Q_CONTACT_NO_TUE`, `ADM.Q_CONTACT_NO_WED`, `ADM.Q_CONTACT_NO_THU`, `ADM.Q_CONTACT_NO_FRI`, `ADM.Q_CONTACT_NO_SAT`
  - **Query:** `contactMapper.insertCustContactHist`
    - **Tables:** `ADM.TCONTACT`
  - **Query:** `orderdtlJMapper.selectOrdItemInfo`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TITEM`
  - **Query:** `vocMapper.selectGenerateVocId`
    - **Tables:** `DUAL`
  - **Query:** `vocMapper.insertVoc`
    - **Tables:** `ADM.TVOC`
  - **Query:** `rewardMapper.insertBndeRwrd`
    - **Tables:** `ADM.TREWARD`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveRewardReasonCode
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveRewardReasonCode`
  - **Query:** `rewardJMapper.selectRewardReasonCode`
    - **Tables:** `ADM.TCODE`

Loaded cached credentials.
### GET /rwrdmng/rwrdmng/retrieveOfferList
- **Service:** `cj.bts.or.srm.domains.rwrdmng.service.RwrdMngService.retrieveOfferList`
  - **Query:** `rewardJMapper.selectOfferList`
    - **Tables:** `ADM.TOFFER`, `ADM.TOFFERMASTER`, `ADM.TUSERS`, `ADM.TDEPT`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obProc/retrieveObProcTgtCust
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveObProcTgtCust`
  - **Query:** `selectObProcTgtCust`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TOBSEG`, `ADM.TOBSC`, `ADM.TORDERDTL`, `ADM.TOBREASONCODE`, `ADM.TCUSTOMER`, `ADM.tcode`, `ADM.tcustomer`, `ADM.tdmsenddtl`, `ADM.TCATALOGREQ`, `ADM.TCUSTDATAERROR`, `ADM.tperinfo`, `ADM.tsegcust`, `ADM.tsegcode`, `ADM.TITEM`, `ADM.TSTOCKPENALTY`, `ADM.tassignscrole`, `ADM.tassignrole`, `ADM.tobsc`

Loaded cached credentials.
### GET /ob/obProc/retrieveObProcTgtDtlHist
-   **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveObProcTgtDtlHist`
    -   **Tables:** `ADM.TOBCUST`, `ADM.TOBPROC`, `ADM.TOB`, `ADM.TOBSEG`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCBCODE`, `ADM.TVENADDINFO`, `ADM.TB2BPRTNR`, `ADM.TOBCALLDTL`

Loaded cached credentials.
### GET /srmng/srerrmng/retrieveScErrDtlHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.retrieveScErrDtlHist`
  - **Query:** `srerrordtlMapper.selectScErrDtlHist`
    - **Tables:** `ADM.TSRERRORDTL`, `ADM.TSRERROR`, `ADM.TPERINFO`


Loaded cached credentials.
### GET /vocmng/voc/retrieveItemRcptWarnInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveItemRcptWarnInfo`
  - **Query:** `vocJMapper.selectItemRcptWarnInfo`
    - **Tables:** `ADM.TDELIVCST`, `ADM.TITEM`, `ADM.TITEMADD`, `ADM.TITEMPOINT`

Loaded cached credentials.
### GET /ob/obProc/retrieveUnReceiptedPublicImpost
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveUnReceiptedPublicImpost`
  - **Query:** `selectUnReceiptedPublicImpost`
    - **Tables:** `ADM.TPRIZECUST`, `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TVIRTUALACNT`

Loaded cached credentials.
### POST /ob/obProc/saveObProcByObObj
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveObProcByObObj`
  - **Tables:** `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### POST /ob/obProc/saveChangeObstandard
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveChangeObstandard`
  - **Query:** `updateObObstandard`
    - **Tables:** `ADM.TOB`

Loaded cached credentials.
### GET /ob/obProc/retrieveRetractionInfo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveRetractionInfo`
  - **Tables:** `ADM.TEXT_NOTRECEIVE`, `ADM.TCODE`, `ADM.TEXTPARTNER_ORDER`, `ADM.TEXT_MAPING_DELIVCOMP`

Loaded cached credentials.
### GET /ob/obProc/retrieveRetractionDeliInfo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveRetractionDeliInfo`
  - **Mapper:** `selectRetractionDeliInfo`
    - **Tables:** `ADM.TEXT_MAPING_DELIVCOMP`

Loaded cached credentials.
### POST /ob/obProc/saveUnrecvDeclWdSmsInf
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveUnrecvDeclWdSmsInf`
  - **Tables:** `ADM.TEXT_NOTRECEIVE`, `ADM.TCODE`, `ADM.TEXTPARTNER_ORDER`, `ADM.TEXT_CELLERID`, `ADM.TEXT_MAPING_DELIVCOMP`, `ADM.TITEM`

Loaded cached credentials.
### POST /ob/obProc/saveResdno
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveResdno`

Loaded cached credentials.
### GET /ob/obProc/retrieveOffer
- **Service:** `cj.bts.or.srm.domains.ob.service.ObOfferService.retrieveOffer`
  - **Tables:** `ADM.TOFFER`, `ADM.TOFFERMASTER`, `ADM.TUSERS`, `ADM.TDEPT`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obProc/retrieveObRstTgtusr
- **Service:** `cj.bts.or.srm.domains.ob.service.ObTgtusrService.retrieveObTgtusrOfferby`
  - **Query:** `ADM.TOBCUST`
    - **Tables:** `ADM.TOBCUST`

Loaded cached credentials.
### GET /ob/obProc/retrieveObRstOffer
- **Service:** `cj.bts.or.srm.domains.ob.service.ObOfferService.retrieveObOffer`
  - **Query:** `selectObOffer`
    - **Tables:** `ADM.TOBOFFER`, `ADM.TOBSEG`, `ADM.TOFFER`, `ADM.TPERINFO`, `ADM.TCODE`


Loaded cached credentials.
### POST /vocmng/voc/retrieveOrderProgressItem
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveOrderProgressItem`
  - **Query:** `orderdtlJMapper.selectOrderProgressItem`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TRESTRICTORDER`, `ADM.TORDER`, `ADM.TORDERETC`, `ADM.TITEMCHNL`, `ADM.TCUSTDCCOUPON`, `ADM.TOFFER`, `ADM.TORDERITEMPROMO`, `ADM.TORDERPRESENT`, `ADM.TITEMADD`, `ADM.TRGLR_PAY_ORD_SCHD`, `ADM.TCBCODE`, `MALLOWN.TORDER_VEN_KEY`, `ADM.TCHANNELSALE`, `MALLOWN.TMCOM_PARTNER_CODE`, `MALLOWN.TPARTNERM`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TPRESHIPREQ`, `ADM.TSECONDDELIVPLAN`, `ADM.THOPEDELY`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`, `ADM.THPOINTSAVEAMT_OM`, `ADM.TINAMT`, `ADM.TPAYCOAPPV`, `ADM.TKAKAOPAYAPPV`, `ADM.TSYRUPAPPV`, `ADM.TPAYNOWPGAPPV`, `ADM.TTVPAYAPPV`, `ADM.TORDER_CPN_PRTNR`, `ADM.TREPAYINFO`, `ADM.TORDERCOST`
  - **Query:** `orderdtlJMapper.selectOrderProgressDetail`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TVOCCODE`, `ADM.TRECEIVER`, `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TPROGRAM`, `ADM.TCODE`, `ADM.TCHANNELSALE`, `ADM.TORDERCANCELDEFER`, `ADM.TRECEIVER_CVS`, `ADM.TCVSRECALLAPPVHIST`, `ADM.TWBILLDTL`, `ADM.TORDERSENDER`, `ADM.TITEMVEN`, `ADM.TORDERPRESENT`, `ADM.TORDERRORD`, `ADM.TITEMADD`, `ADM.TRGLR_PAY_ORD_SCHD`, `ADM.TCBCODE`, `MALLOWN.TORDER_VEN_KEY`, `ADM.TWBILL`, `ADM.TPRESHIPREQ`, `ADM.TSECONDDELIVPLAN`, `ADM.THOPEDELY`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`, `ADM.TORDERRETURN_RSN`
  - **Query:** `orderdtlJMapper.selectOrderAddInfo1`
    - **Tables:** `ADM.TORDERDTL`
  - **Query:** `orderdtlJMapper.selectOrderAddInfo2`
    - **Tables:** `ADM.TORDERCANCELDEFER`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`

Loaded cached credentials.
### GET /ob/obProc/retrieveObRstSeg
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveObRstSeg`
  - **Tables:** `ADM.TOBSEG`, `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obProc/saveObRst
- **Service:** `cj.bts.or.srm.domains.ob.service.ObOfferService.saveObOffer`
  - **Tables:** `ADM.TOBOFFER`

Loaded cached credentials.
### POST /ob/obProc/saveObRstProcCmplt
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveObRstSeg`
  - **Tables:** `ADM.TOBSEG`

Loaded cached credentials.
### POST /ob/obProc/saveObRwrdPaymt
- **Service:** `cj.bts.or.srm.domains.ob.service.ObOfferService.saveObRwrdPaymt`
  - **Query:** `rewardMapper.insertRwrd`
    - **Tables:** `ADM.TREWARD`
  - **Query:** `rewardMapper.updateRwrd`
    - **Tables:** `ADM.TREWARD`
  - **Query:** `rewardJMapper.selectRepayProgCd`
    - **Tables:** `ADM.TREPAY`
  - **Query:** `rewardMapper.deleteRwrd`
    - **Tables:** `ADM.TREWARD`

Loaded cached credentials.
### GET /ob/obProc/retrieveObProcCallDtl
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveObProcCallDtl`
  - **Query:** `selectObProcCallDtl`
    - **Tables:** `ADM.TOBPROC`, `ADM.TOBCALLDTL`, `ADM.TOBSC`, `ADM.TCODE`, `ADM.TPERINFO`, `ADM.TCUSTOMER`

Loaded cached credentials.
### POST /ob/obProc/modifyGiftPrzusrInf
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.modifyGiftPrzusrInf`
  - **Tables:** `ADM.TOBCUST`

Loaded cached credentials.
### GET /ob/obProc/retrieveRefInfo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveArsDeliveryPlace`
  - **Query:** `selectArsDeliveryPlace`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### GET /ob/obProc/retrieveCustObProcHist
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveCustObProcHist`
  - **Query:** `selectCustObProcHist`
    - **Tables:** `ADM.TOBCUST`, `ADM.TOBSEG`, `ADM.TOB`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TOBCALLDTL`, `ADM.TVOCCODE`

Loaded cached credentials.
### POST /ob/obProc/payObOffer
- **Service:** `cj.bts.or.srm.domains.ob.service.ObOfferService.payObOffer`
  - **Tables:** `ADM.TOFFER`, `ADM.TOFFERMASTER`

Loaded cached credentials.
### GET /ob/obProc/retrieveRevcNoByOrdNo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveRevcNoByOrdNo`
  - **Query:** `selectRevcNoByOrdNo`
    - **Tables:** `ADM.TEXTPARTNER_ORDER`

Loaded cached credentials.
### GET /vocmng/voc/retrieveOrderProgressDetail
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveOrderProgressDetail`
  - **Query:** `orderdtlJMapper.selectOrderProgressDetail`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TVOCCODE`, `ADM.TRECEIVER`, `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TPROGRAM`, `ADM.TCODE`, `ADM.TCHANNELSALE`, `ADM.TORDERCANCELDEFER`, `ADM.TRECEIVER_CVS`, `ADM.TCVSRECALLAPPVHIST`, `ADM.TWBILLDTL`, `ADM.TORDERSENDER`, `ADM.TITEMVEN`, `ADM.TORDERPRESENT`, `ADM.TORDERRORD`, `ADM.TITEMADD`, `ADM.TRGLR_PAY_ORD_SCHD`, `ADM.TCBCODE`, `MALLOWN.TORDER_VEN_KEY`, `ADM.TWBILL`, `ADM.TPRESHIPREQ`, `ADM.TSECONDDELIVPLAN`, `ADM.THOPEDELY`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`, `ADM.TORDERRETURN_RSN`

Loaded cached credentials.
### GET /ob/obSchd/selectObProgMdt
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSchdService.retrieveObProgMdt`
  - **Query:** `selectObTgtusrItemby`
    - **Tables:** `ADM.TOB`, `ADM.TOBSEG`, `ADM.TOBCUST`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TOBSC`, `ADM.TVENDOR`, `ADM.TMD`, `ADM.TSTRUCTUREMON`, `ADM.TOBREASONCODE`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TCUSTOMER`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obSchd/saveObProgMdt
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSchdService.saveObProgMdt`
  - **Tables:** `ADM.TOBCUST`

Loaded cached credentials.
### POST /ob/obSchd/completeObMdtForce
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSchdService.saveObForceCmplt`
  - **Query:** `updateObTgtusrForceCmplt`
    - **Tables:** `ADM.TOBCUST`, `DUAL`
  - **Query:** `insertObProcs`
    - **Tables:** `ADM.TOBPROC`, `DUAL`, `ADM.TOBSC`


Loaded cached credentials.
### GET /ob/obRsn/retrieveObRsnCd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnService.retrieveObRsnCd`
  - **Query:** `selectObRsnCd`
    - **Tables:** `ADM.TOBREASONCODE`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /ob/obRsn/saveObRsnCd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnService.saveObRsnCd`
  - **Tables:** `ADM.TOBREASONCODE`

Loaded cached credentials.
### GET /ob/obObjMoCrt/retrieveObSegSeqNm
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.retrieveObSegs`
  - **Tables:** `ADM.TOBSEG`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obObjMoCrt/retrieveObObjWithProgCd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.retrieveObObjWithProgCd`
  - **Query:** `selectObObjWithProgCd`
    - **Tables:** `ADM.TOB`, `ADM.TOBSEG`, `ADM.TOBCUST`, `ADM.TCUSTOMER`, `ADM.TCODE`, `ADM.TPERINFO`


Loaded cached credentials.
### POST /ob/obObjMoCrt/storeObObjByArsDeliveryPlace
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.storeObObjsByArsDeliveryPlace`
  - **Tables:** `ADM.TOBCUST`, `ADM.TARSRECEIVER`, `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TCUSTADDR`, `ADM.TCUSTOMER`, `ADM.TOB`, `ADM.TOBSEG`

Loaded cached credentials.
### POST /ob/obObjMoCrt/storeObObjByUnReceiptedPublicImpost
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.storeObObjByUnReceiptedPublicImpost`
  - **Tables:** `ADM.TOBCUST`, `ADM.TPRIZECUST`, `ADM.TCUSTADDR`, `ADM.TCUSTOMER`, `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TCBCODE`, `ADM.TOB`, `ADM.TOBSEG`

Loaded cached credentials.
### POST /ob/obObjMoCrt/storeObObjByCallBack
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.storeObObjsByCallBack`
  - **Tables:** `ADM.TOBCUST`, `ADM.TORDCALLBACK`, `ADM.TOB`, `ADM.TOBSEG`

Loaded cached credentials.
### GET /vocmng/voc/retrieveOrderDetailDeliv
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveOrderDetailDeliv`
  - **Query:** `orderdtlJMapper.selectOrderDetailDeliv`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TRECEIVER`, `ADM.TITEMCHNL`, `ADM.TCHANNEL`, `ADM.TORDER`, `ADM.TORDITEMDELIVCST`, `ADM.TORDERCOST`, `ADM.TORDERCOSTADD`, `ADM.TORDERRORD`, `ADM.TORDERRETURN_RSN`

Loaded cached credentials.
### POST /ob/obObjMoCrt/storeObObjByStockInsufficiency
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.storeObObjsByStockInsufficiency`
  - **Tables:** `ADM.TOBCUST`, `ADM.TSTOCKPENALTY`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TRECEIVER`, `ADM.TOB`, `ADM.TOBSEG`

Loaded cached credentials.
### POST /ob/obObjMoCrt/storeObObjByObObjYn
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.modifyObObjsByObObjYn`
  - **Tables:** `ADM.TOBCUST`

Loaded cached credentials.
### GET /ob/retrieveSlItemUnit
- **Service:** `cj.bts.or.srm.domains.ob.service.ObReqService.retrieveSlItemUnit`
  - **Query:** `selectSlItemUnit`
    - **Tables:** `ADM.TITEM`

Loaded cached credentials.
### POST /ob/surveyReg/insertBndeSetlFixProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.insertBndeSetlFixProc`
  - **Tables:** `Adm.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/insertSurveySeg
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.insertSurveySeg`
  - **Mapper:** `insertSurveySeg`
    - **Tables:** `adm.TSURVEYSEG`

Loaded cached credentials.
### POST /ob/surveyReg/updateSurvey
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.updateSurvey`
  - **Query:** `updateSurvey`
    - **Tables:** `ADM.TSURVEY`

Loaded cached credentials.
### GET /ob/surveyReg/retrieveSurvey
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.inquireSurveyReg`
  - **Query:** `inquireSurveyReg`
    - **Tables:** `adm.TSURVEY`, `adm.TCODE`, `adm.TITEMKINDS`, `adm.TDEPT`, `adm.TPERINFO`

Loaded cached credentials.
### POST /ob/surveyReg/saveSurveySbmtProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.modifySurveyRegForSubmit`
  - **Query:** `modifySurveyRegForSubmit`
    - **Tables:** `ADM.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/saveSurveySbmtCncl
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.modifySurveyRegForSubmitCancel`
  - **Query:** `ADM.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/deleteSurveyOb
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.deleteSurveyOb`
  - **Mapper:** `deleteSurveyOb`
    - **Tables:** `ADM.TOB`

Loaded cached credentials.
### GET /vocmng/voc/retrieveOrderPayInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveOrderPayInfo`
  - **Query:** `orderdtlJMapper.selectOrderPayInfo`
    - **Tables:** `ADM.TINAMT`, `ADM.TBANK`, `ADM.TBIZ`, `ADM.TINAMT_PAR_CNCL`

Loaded cached credentials.
### POST /ob/surveyReg/saveSurveyAppvCncl
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.modifySurveyRegForAppvCancel`
  - **Query:** `modifySurveyRegForAppvCancel`
    - **Tables:** `ADM.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/saveSurveyAppvProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.modifySurveyRegForAppv`
  - **Query:** `modifySurveyRegForAppv`
    - **Tables:** `ADM.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/updateSurveyGrtwrd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.modifySurveyRegGreet`
  - **Query:** `ADM.TSURVEY`
    - **Tables:** `ADM.TSURVEY`

Loaded cached credentials.
### POST /ob/surveyReg/insertOpnQstitemKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.insertOpnQstitemKwd`
  - **Query:** `insertSurveyKeyWord`
    - **Tables:** `ADM.TSURVEYKEYWORD`

Loaded cached credentials.
### POST /ob/surveyReg/deleteOpnQstitemKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.deleteOpnQstitemKwd`
  - **Query:** `ADM.TSURVEYKEYWORD`
    - **Tables:** `ADM.TSURVEYKEYWORD`

Loaded cached credentials.
### POST /ob/surveyReg/updateOpnQstitemKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.updateOpnQstitemKwd`
  - **Query:** `updateOpnQstitemKwd`
    - **Tables:** `ADM.TSURVEYKEYWORD`

Loaded cached credentials.
### GET /ob/surveyReg/selectOpnQstitemKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.selectOpnQstitemKwd`
  - **Query:** `selectOpnQstitemKwd`
    - **Tables:** `TSURVEYKEYWORD`

Loaded cached credentials.
### POST /ob/surveyReg/insertOpnQstitemRlKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.insertOpnQstitemRlKwd`
  - **Query:** `insertOpnQstitemRlKwd`
    - **Tables:** `ADM.TSURVEYRELKEYWORD`

Loaded cached credentials.
### POST /ob/surveyReg/deleteOpnQstitemRlKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.deleteOpnQstitemRlKwd`
  - **Query:** `deleteSurveyRelKeyWord`
    - **Tables:** `ADM.TSURVEYRELKEYWORD`

Loaded cached credentials.
### POST /ob/surveyReg/updateOpnQstitemRlKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.updateOpnQstitemRlKwd`
  - **Query:** `modifySurveyRelKeyWord`
    - **Tables:** `ADM.TSURVEYRELKEYWORD`

Loaded cached credentials.
### GET /vocmng/voc/retrieveOrderProgressItemList
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveOrderProgressItemList`
  - **Query:** `orderdtlJMapper.selectOrderProgressItem2`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TRESTRICTORDER`, `ADM.TORDER`, `ADM.TORDERETC`, `ADM.TITEMCHNL`, `ADM.TCUSTDCCOUPON`, `ADM.TOFFER`, `ADM.TORDERITEMPROMO`, `ADM.TORDERPRESENT`, `ADM.TITEMADD`, `ADM.TRGLR_PAY_ORD_SCHD`, `ADM.TCBCODE`, `MALLOWN.TORDER_VEN_KEY`, `ADM.TCHANNELSALE`, `MALLOWN.TMCOM_PARTNER_CODE`, `MALLOWN.TPARTNERM`, `ADM.TWBILL`, `ADM.TWBILLDTL`, `ADM.TPRESHIPREQ`, `ADM.TSECONDDELIVPLAN`, `ADM.THOPEDELY`, `ADM.TORDERCANCELTARGET`, `ADM.TSTOCKPENALTY`, `ADM.THPOINTSAVEAMT_OM`, `ADM.TINAMT`, `ADM.TPAYCOAPPV`, `ADM.TKAKAOPAYAPPV`, `ADM.TSYRUPAPPV`, `ADM.TPAYNOWPGAPPV`, `ADM.TTVPAYAPPV`, `ADM.TORDER_CPN_PRTNR`, `ADM.TREPAYINFO`, `ADM.TORDERCOST`

Loaded cached credentials.
### GET /ob/surveyReg/selectOpnQstitemRlKwd
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.selectOpnQstitemRlKwd`
  - **Query:** `selectOpnQstitemRlKwd`
    - **Tables:** `ADM.TSURVEYRELKEYWORD`


Loaded cached credentials.
### GET /ob/surveyReg/selectSurveyNo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObSurveyRegService.selectSurveyNo`
  - **Query:** `selectSurveyNo`
    - **Tables:** `TSURVEY`

Loaded cached credentials.
### POST /ob/obTgtusr/createObTgtusrRe
- **Service:** `cj.bts.or.srm.domains.ob.service.ObTgtusrService.createObTgtusrRe`
  - **Tables:** `ADM.TOB`, `ADM.TOBSEG`, `ADM.TOBCUST`

Loaded cached credentials.
### POST /ob/obTgtusr/saveObTgtusrBatch
- **Service:** `cj.bts.or.srm.domains.ob.service.ObTgtusrService.saveObTgtusrBatch`
  - **Tables:** `ADM.TOBCUST`, `ADM.TORDER_CPN_PRTNR`

Loaded cached credentials.
### GET /ob/ntcn/retrieveOrdSmsSendGb
- **Service:** `cj.bts.or.srm.domains.ob.service.NtcnService.retrieveOrdSmsSendGb`
  - **Query:** `selectOrdSmsSendGb`
    - **Tables:** `ADM.TCODE`, `ADM.TCHR_MSG_TMPL`

Loaded cached credentials.
### POST /ob/ntcn/saveSendSmsMessage
- **Service:** `cj.bts.or.srm.domains.ob.service.NtcnService.saveSendSmsMessage`
  - **Tables:** `DUAL`, `ADM.TCONTACT`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/ntcn/saveSendMmsMessage
- **Service:** `cj.bts.or.srm.domains.ob.service.NtcnService.saveSendMmsMessage`
  - **Tables:** `DUAL`, `ADM.TCONTACT`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/ntcn/retrieveSmsBrandInfo
- **Service:** `cj.bts.or.srm.domains.ob.service.NtcnService.retrieveSmsBrandInfo`
  - **Query:** `selectSmsBrandInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TBRAND`

Loaded cached credentials.
### POST /ob/ntcn/saveVocData
- **Service:** `cj.bts.or.srm.domains.ob.service.NtcnService.saveVocData`
  - **Tables:** `ADM.TITEM`

Loaded cached credentials.
### GET /ob/obScAssign/retrieveScWorkpartTree
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.retrieveScWorkpartTree`
  - **Tables:** `ADM.TCCWORKPART`, `ADM.TCOMPANY`, `ADM.TCODE`, `ADM.TWORKPART`, `ADM.TSCWORKPART`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/voc/retrieveBbsUnprocCcnt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveBbsUnprocCcnt`
  - **Query:** `bbsJMapper.selectBbsUnprocCcnt`
    - **Tables:** `DUAL`, `ADM.TSR`, `ADM.TMALLBOARD`, `ADM.TSTOCKPENALTY`, `ADM.TORDERITEM`, `ADM.TORDERCANCELTARGET`

Loaded cached credentials.
### GET /ob/obScAssign/retrieveObObSeg
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.retrieveObObSeg`
  - **Query:** `ADM.TOBSEG`
    - **Tables:** `ADM.TOBSEG`
  - **Query:** `ADM.TOB`
    - **Tables:** `ADM.TOB`
  - **Query:** `ADM.TOBREASONCODE`
    - **Tables:** `ADM.TOBREASONCODE`
  - **Query:** `ADM.TDEPT`
    - **Tables:** `ADM.TDEPT`
  - **Query:** `ADM.TPERINFO`
    - **Tables:** `ADM.TPERINFO`
  - **Query:** `ADM.TCODE`
    - **Tables:** `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obScAssign/retrieveObSegSeqNm
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.retrieveObSegSeqNm`
  - **Tables:** `ADM.TOBSEG`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obScAssign/retrieveObObj
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.retrieveObObj`
  - **Query:** `selectObObjs`
    - **Tables:** `ADM.TOBCUST`, `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obScAssign/storeObScAssign
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.assignObToSc`
  - **Query:** `ADM.TOBCUST`
  - **Query:** `ADM.TOB`
  - **Query:** `ADM.TOBSEG`
  - **Query:** `ADM.TCUSTOMER`
  - **Query:** `ADM.TPERINFO`
  - **Query:** `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obScAssign/storeObScAssignChange
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.assignChangeObToSc`
  - **Tables:** `ADM.TOBCUST`, `ADM.TOB`, `ADM.TOBSEG`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obScAssign/retrieveObReq
- **Service:** `cj.bts.or.srm.domains.ob.service.ObScAssignService.retrieveObReq`
  - **Query:** `selectObReq`
    - **Tables:** `ADM.TOB`, `ADM.TCODE`, `ADM.TOBREASONCODE`, `ADM.TDEPT`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObClsbyProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObClsbyProc`
  - **Query:** `selectObClsbyProc`
    - **Tables:** `ADM.TOBSCDSUM`, `ADM.TOBREASONCODE`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObRsnSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObRsnSttus`
  - **Query:** `selectObRsnSttus`
    - **Tables:** `ADM.tob`, `ADM.tobcust`, `ADM.torderdtl`, `ADM.titem`, `ADM.tstructuremon`, `ADM.tmd`, `ADM.tscworkpart`, `ADM.tobreasoncode`, `ADM.tcode`, `ADM.tperinfo`, `ADM.TMD`, `ADM.TMDLINK`, `ADM.TVENDOR`, `adm.tstructure`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObDtlProcSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObDtlProcSttus`
  - **Tables:** `ADM.tobcalldtl`, `ADM.tscworkpart`, `ADM.tworkpart`, `ADM.tstructuremon`, `ADM.tstructure`, `ADM.tmdlink`, `ADM.tcontact`, `ADM.titem`, `ADM.tperinfo`, `ADM.tvoccode`, `ADM.tcode`, `ADM.titemkinds`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObBizwrkProcSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObBizwrkProcSttus`
  - **Tables:** `adm.tob`, `adm.tobreasoncode`, `adm.tobcust`, `adm.tobsc`, `adm.tperinfo`

Loaded cached credentials.
### GET /vocmng/voc/retrieveCustInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveCustInfo`
  - **Query:** `vocJMapper.selectCustInfo`
    - **Tables:** `ADM.TCUSTOMER`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObCallGnrlz
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObCallGnrlz`
  - **Query:** `ADM.tobcalldtl`
    - **Tables:** `ADM.tobcalldtl`
  - **Query:** `ADM.tscworkpart`
    - **Tables:** `ADM.tscworkpart`
  - **Query:** `ADM.tworkpart`
    - **Tables:** `ADM.tworkpart`
  - **Query:** `ADM.tstructuremon`
    - **Tables:** `ADM.tstructuremon`
  - **Query:** `ADM.tstructure`
    - **Tables:** `ADM.tstructure`
  - **Query:** `ADM.tmdlink`
    - **Tables:** `ADM.tmdlink`
  - **Query:** `ADM.tcontact`
    - **Tables:** `ADM.tcontact`
  - **Query:** `ADM.tvoccode`
    - **Tables:** `ADM.tvoccode`
  - **Query:** `ADM.tcode`
    - **Tables:** `ADM.tcode`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObExecSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObExecSttus`
  - **Query:** `selectObExecSttus`
    - **Tables:** `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TOBCUST`, `ADM.TOBSC`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /ob/obAgg/retrieveSCObCallReport
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveSCObCallReport`
  - **Query:** `ADM.TOBCALLDTL`
    - **Tables:** `ADM.TOBCALLDTL`
  - **Query:** `ADM.TSCWORKPART`
    - **Tables:** `ADM.TSCWORKPART`
  - **Query:** `ADM.TWORKPART`
    - **Tables:** `ADM.TWORKPART`
  - **Query:** `ADM.TSTRUCTUREMON`
    - **Tables:** `ADM.TSTRUCTUREMON`
  - **Query:** `ADM.TSTRUCTURE`
    - **Tables:** `ADM.TSTRUCTURE`
  - **Query:** `ADM.TMDLINK`
    - **Tables:** `ADM.TMDLINK`
  - **Query:** `ADM.TITEM`
    - **Tables:** `ADM.TITEM`
  - **Query:** `ADM.TCONTACT`
    - **Tables:** `ADM.TCONTACT`
  - **Query:** `ADM.TCODE`
    - **Tables:** `ADM.TCODE`
  - **Query:** `ADM.tdept`
    - **Tables:** `ADM.TDEPT`
  - **Query:** `ADM.tperinfo`
    - **Tables:** `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obAgg/retrieveSCObSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveSCObSttus`
  - **Tables:** `ADM.tobcalldtl`, `ADM.tscworkpart`, `ADM.tworkpart`, `ADM.tdept`, `ADM.tstructuremon`, `ADM.tstructure`, `ADM.tmdlink`, `ADM.tcontact`, `ADM.tperinfo`, `ADM.tvoccode`, `ADM.tcode`

Loaded cached credentials.
### GET /ob/obAgg/retrieveSCProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveSCProc`
  - **Query:** `selectSCProc`
    - **Tables:** `ADM.tobscdsum`, `ADM.tobreasoncode`, `adm.tdept`, `adm.tworkpart`, `adm.tperinfo`

Loaded cached credentials.
### GET /ob/obAgg/retrieveCustbyObHist
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveCustbyObHist`
  - **Service:** `cj.bts.or.srm.domains.ob.service.ObTgtusrService.retrieveObTgtusrCustbyHist`
    - **Query:** `selectObTgtusrCustbyHist`
      - **Tables:** `ADM.TOBCUST`, `ADM.TOBSEG`, `ADM.TOB`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TOBCALLDTL`, `ADM.TVOCCODE`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obAgg/retrieveSaleDeptObSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveSaleDeptObSttus`
  - **Query:** `selectSaleDeptObSttus`
    - **Tables:** `ADM.tobcalldtl`, `ADM.tscworkpart`, `ADM.tworkpart`, `ADM.tstructuremon`, `ADM.tstructure`, `ADM.tmdlink`, `ADM.tcontact`, `ADM.titem`, `ADM.tvoccode`, `ADM.tcode`, `ADM.titemkinds`

Loaded cached credentials.
### GET /ob/obAgg/retrieveSaleDeptRsnAgg
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveSaleDeptRsnAgg`
  - **Query:** `selectSaleDeptRsnAgg1`
    - **Tables:** `ADM.TOB`, `ADM.TCODE`, `ADM.TOBCUST`
  - **Query:** `selectSaleDeptRsnAgg2`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TCODE`
  - **Query:** `selectSaleDeptRsnAgg3`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`


Loaded cached credentials.
### GET /ob/obAgg/retrieveDtbyObProc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveDtbyObProc`
  - **Query:** `selectDtbyObProc`
    - **Tables:** `ADM.TOBSCDSUM`, `ADM.TOBREASONCODE`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObProcItembyStatsSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObProcItembyStatsSttus`
  - **Query:** `obAggMapper.selectObProcItembyStatsSttus`
    - **Tables:** `ADM.tob`, `ADM.tobcust`, `ADM.torderitem`, `ADM.titem`, `ADM.tobsc`, `ADM.tdept`, `ADM.tworkpart`, `ADM.tperinfo`, `ADM.tobproc`, `ADM.TITEM`

Loaded cached credentials.
### GET /vocmng/voc/retrieveSrExistCheck
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrExistCheck`
  - **Query:** `srJMapper.selectSrExistCheck`
    - **Tables:** `ADM.TSR`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObProcRealtmStatsSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObProcRealtmStatsSttus`
  - **Query:** `selectObProcRealtmStatsSttus`
    - **Tables:** `ADM.tobcust`, `ADM.tdept`, `ADM.tworkpart`, `ADM.tperinfo`, `ADM.tobproc`, `ADM.tobreasoncode`, `ADM.tob`, `ADM.tobsc`

Loaded cached credentials.
### GET /ob/obAgg/retrieveObProcStatsSttus1
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveObProcStatsSttus1`
  - **Query:** `selectObProcStatsSttus`
    - **Tables:** `ADM.TOBSCDSUM`, `ADM.tdept`, `ADM.tworkpart`, `ADM.tperinfo`, `ADM.TOBCUST`

Loaded cached credentials.
### GET /ob/obAgg/retrieveDtbyObProcStatsSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.retrieveDtbyObProcStatsSttus`
  - **Query:** `selectDtbyObProcStatsSttus`
    - **Tables:** `ADM.tobcust`, `ADM.tobsc`, `ADM.tobproc`, `ADM.tob`, `ADM.tobreasoncode`, `ADM.tdept`, `ADM.tworkpart`, `ADM.tperinfo`

Loaded cached credentials.
### POST /ob/obAgg/excelSaleDeptObSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.excelSaleDeptObSttus`
  - **Tables:** `ADM.TOBCALLDTL`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TMDLINK`, `ADM.TCONTACT`, `ADM.TITEM`, `ADM.TCODE`, `ADM.TITEMKINDS`

Loaded cached credentials.
### POST /ob/obAgg/excelSCObCallReport
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.excelSCObCallReport`
  - **Query:** `selectSCObCallReportExcel`
    - **Tables:** `ADM.TOBCALLDTL`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TMDLINK`, `ADM.TITEM`, `ADM.TCONTACT`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obAgg/excelSCObSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.excelSCObSttus`
  - **Tables:** `ADM.TOBCALLDTL`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TMDLINK`, `ADM.TCONTACT`, `ADM.TCODE`

Loaded cached credentials.
### POST /ob/obAgg/excelObDtlProcSttus
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.excelObDtlProcSttus`
  - **Query:** `selectObDtlProcSttusExcel`
    - **Tables:** `ADM.TOBCALLDTL`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TMDLINK`, `ADM.TCONTACT`, `ADM.TITEM`, `ADM.TCODE`, `ADM.TITEMKINDS`

Loaded cached credentials.
### POST /ob/obAgg/saveObCallDtl
- **Service:** `cj.bts.or.srm.domains.ob.service.ObAggService.saveObCallDtl`
  - **Query:** `obcalldtlMapper.insertObCallDtl`
    - **Tables:** `ADM.TOBCALLDTL`
  - **Query:** `obcalldtlMapper.updateObCallDtl`
    - **Tables:** `ADM.TOBCALLDTL`
  - **Query:** `orderdtlMapper.selectChnCd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObRsnGrp
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObRsnGrp`
  - **Query:** `selectObRsnGrp`
    - **Tables:** `ADM.TCODE`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReq
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReqList`
  - **Query:** `selectObs`
    - **Tables:** `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSURVEY`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TOBCUST`

Loaded cached credentials.
### GET /vocmng/voc/retrieveBbsVocCount
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveBbsVocCount`
  - **Query:** `vocMapper.selectBbsVocCount`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReqExtract
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReqExtract`
  - **Query:** `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TORDERITEM`, `ADM.TCODE`, `ADM.TVENDOR`

Loaded cached credentials.
### POST /ob/obRsnGrp/storeObReq
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.insertObWithObjs`
  - **Tables:** `ADM.TOBCUST`, `ADM.TOB`, `ADM.TOBSEG`, `ADM.TORDERDTL`
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.storeObs`
  - **Tables:** `ADM.TOB`


Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReqNoTitle
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReqNoTitle`
  - **Tables:** `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSURVEY`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TOBCUST`

Loaded cached credentials.
### POST /ob/obRsnGrp/saveObSeg
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.saveObSeg`
  - **Query:** `insertObSeg`
    - **Tables:** `ADM.TOBSEG`
  - **Query:** `updateObSeg`
    - **Tables:** `ADM.TOBSEG`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReqByObNo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReq`
  - **Query:** `selectObReq`
    - **Tables:** `ADM.TOB`, `ADM.TCODE`, `ADM.TOBREASONCODE`, `ADM.TDEPT`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obRsnGrp/getObReq
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReq`
  - **Query:** `selectOb`
    - **Tables:** `ADM.TOB`, `ADM.TCODE`, `ADM.TOBREASONCODE`, `ADM.TDEPT`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveSurveyByObNo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveSurveyByObNo`
  - **Query:** `selectSurveyByObNo`
    - **Tables:** `ADM.TOB`, `ADM.TSURVEY`, `ADM.TCODE`, `ADM.TITEMKINDS`, `ADM.TDEPT`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrievePromotionByObNo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrievePromotionByObNo`
  - **Query:** `obRsnGrpMapper.selectPromotionByObNo`
    - **Tables:** `ADM.TOB`, `ADM.TPROM`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReqByRunDt
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReqByRunDt`
  - **Tables:** `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSURVEY`, `ADM.TITEMPRICE`, `ADM.TITEMCHNL`, `ADM.TITEM`, `ADM.TVENDOR`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObReasonCodeName
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReasonCodeName`
  - **Query:** `ADM.TCODE`
    - **Tables:** `ADM.TCODE`

Loaded cached credentials.
### POST /srmng/srerrmng/saveScErrDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.saveScErrDtl`
  - **Query:** `srerrordtlMapper.insertScErrDtl`
    - **Tables:** `ADM.TSRERRORDTL`
  - **Query:** `srerrordtlMapper.modifySrErrAdj`
    - **Tables:** `ADM.TSRERROR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveSrRcptDtlHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrRcptDtlHist`
  - **Query:** `srJMapper.selectSrRcptDtl`
    - **Tables:** `ADM.TSR`, `ADM.TVOCCODE`, `ADM.TPERINFO`
  - **Query:** `srJMapper.selectSrProcMandClau`
    - **Tables:** `ADM.TVOCADMDATA`, `ADM.TADMCODE`, `ADM.TSR`, `ADM.TVOCADMCODE`

Loaded cached credentials.
### POST /ob/obRsnGrp/deleteObCust
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.deleteObCust`
  - **Query:** `deleteObCust`
    - **Tables:** `ADM.TOBCUST`

Loaded cached credentials.
### POST /ob/obRsnGrp/storeObObjByErrorInfo
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.storeObObjByErrorInfo`
  - **Tables:** `ADM.TOBCUST`, `ADM.TOBREASONCODE`, `ADM.TOB`, `ADM.TOBSEG`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveDeleteObState
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObReqList`
  - **Tables:** `ADM.TOB`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TSURVEY`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TOBCUST`

Loaded cached credentials.
### GET /ob/obRsnGrp/retrieveObMailToMember
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.retrieveObMailToMember`
  - **Query:** `selectObMailToMember`
    - **Tables:** `ADM.TCBCODE`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /ob/obRsnGrp/storeObMailSending
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.convertObDtoToEntity`
- **Service:** `cj.bts.or.srm.domains.ob.service.ObRsnGrpService.obResultEmailSending`
  - **Mapper:** `obcustMapper.selectObResult`
    - **Tables:** `ADM.TOBCUST`, `ADM.TREWARD`
  - **Mapper:** `perinfoMapper.selectObResultMember`
    - **Tables:** `ADM.TPERINFO`, `ADM.TCBCODE`
  - **Mapper:** `perinfoMapper.selectObResultMemberNew`
    - **Tables:** `ADM.TPERINFO`
  - **Mapper:** `perinfoMapper.selectMailToMemberNew`
    - **Tables:** `ADM.TPERINFO`, `ADM.TCBCODE`, `ADM.TOB`, `ADM.TCODE`
  - **Mapper:** `perinfoMapper.selectMailToMember`
    - **Tables:** `ADM.TPERINFO`, `ADM.TCBCODE`
  - **Mapper:** `obMapper.updateObMailSendingDate`
    - **Tables:** `ADM.TOB`

Loaded cached credentials.
### GET /ccWorkpart/retrieveWorkpartByCenter
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.WorkpartByCenterService.retrieveWorkpartByCenter`
  - **Query:** `selectCcWorkpart`
    - **Tables:** `ADM.TCCWORKPART`, `ADM.TWORKPART`

Loaded cached credentials.
### POST /ccWorkpart/saveWorkpartByCenter
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.WorkpartByCenterService.saveWorkpartByCenter`
  - **Query:** `updateWorkpartByCenterInfo`
    - **Tables:** `ADM.TCCWORKPART`
  - **Query:** `selectWorkpart`
    - **Tables:** `ADM.TWORKPART`
  - **Query:** `insertWorkpartByCenterInfo`
    - **Tables:** `ADM.TCCWORKPART`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveGiftPrizeOffer
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveGiftPrizeOffer`
  - **Query:** `selectGiftPrizeOffer`
    - **Tables:** `ADM.TPRIZECUST`, `ADM.TPROMOFFER`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveGiftPrizeOrdItem
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveGiftPrizeOrdItem`
  - **Query:** `selectGiftPrizeOrdItem`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERITEM`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveCustbyGiftProm
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveCustbyGiftProm`
  - **Query:** `selectCustbyGiftProm`
    - **Tables:** `ADM.TPRIZECUST`, `ADM.TPROM`, `ADM.TPROMOFFER`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveCustCounReqt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveCustCounReqt`
  - **Query:** `srJMapper.selectCustCounReqt`
    - **Tables:** `ADM.TSR`, `ADM.TCUSTOMER`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveSbxScriptMgrpCode
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveSbxScriptMgrpCode`
  - **Query:** `retrieveSbxScriptMgrpCode`
    - **Tables:** `ADM.TANSBANKCODE`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveSbxScriptSgrpCode
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveSbxScriptSgrpCode`
  - **Query:** `retrieveSbxScriptSgrpCode`
    - **Tables:** `ADM.TANSBANKCODE`, `ADM.TCODE`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveScript
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveScript`
  - **Query:** `retrieveScript`
    - **Tables:** `ADM.TANSBANK`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /rsponsKnwldg/saveScript
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.saveScript`
  - **Query:** `insertScript`
    - **Tables:** `ADM.TANSBANK`
  - **Query:** `updateScript`
    - **Tables:** `ADM.TANSBANK`

Loaded cached credentials.
### GET /rsponsKnwldg/retrieveInfBank
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.retrieveInfBank`
  - **Query:** `retrieveInfBank`
    - **Tables:** `ADM.TINFOBANK`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSR`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TCCWORKPART`, `ADM.TCODE`, `ADM.TMDLINK`


Loaded cached credentials.
### POST /rsponsKnwldg/saveInfBank
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.RsponsKnwldgService.saveInfBank`
  - **Query:** `infobankMapper.updateInfBank`
    - **Tables:** `ADM.TINFOBANK`
  - **Query:** `infobankCcmsMapper.selectInfBankCcmsDup`
    - **Tables:** `ADM.TINFOBANK_CCMS`
  - **Query:** `infobankCcmsMapper.insertInfBankCcms`
    - **Tables:** `ADM.TINFOBANK_CCMS`, `ADM.TINFOBANK`, `ADM.TCODE`, `ADM.TITEMKINDS`, `ADM.TVENDOR`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /custQestnAns/retrieveCjmallEvent
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.retrieveCjmallEvent`
  - **Query:** `selectEntprsProm`
    - **Tables:** `ADM.TPROMDTL`, `ADM.TPROM`, `ADM.TOFFERITEM`, `ADM.TITEM`, `ADM.TPROMOFFER`, `ADM.TDEPT`, `ADM.TCODE`
  - **Query:** `selectPromEvent`
    - **Tables:** `ADM.TPROMDTL`, `ADM.TPROM`, `ADM.TPROMOFFER`, `ADM.TOFFER`, `ADM.TDEPT`, `ADM.TCODE`, `ADM.TWEBEVENTM`, `ADM.TSHOP`, `ADM.TPERINFO`, `ADM.TSURVEY`

Loaded cached credentials.
### GET /custQestnAns/retrieveBdMonitorInf
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.retrieveBdMonitorInf`
  - **Query:** `selectBdMonitorInfAsCatv`
    - **Tables:** `ADM.TBDSCHEDTL`, `ADM.TPGMMONITOR`, `ADM.TITEM`, `ADM.TPROGRAM`
  - **Query:** `selectBdMonitorInfAsSatelite`
    - **Tables:** `ADM.TBDSCHEDTL`, `ADM.TPGMMONITOR`, `ADM.TITEM`, `ADM.TPROGRAM`, `ADM.TCODE`
  - **Query:** `selectBdMonitorInfAsShock`
    - **Tables:** `MALLOWN.TITV_ITEM`, `MALLOWN.TITV_PGM`, `MALLOWN.TITV_PGM_GROUP`, `ADM.TITEMCHNL`, `MALLOWN.TITV_VOD`, `ADM.TPGMMONITOR`

Loaded cached credentials.
### GET /custQestnAns/retrieveBdMonitorQestnAns
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.retrieveBdMonitorQestnAns`
  - **Query:** `selectBdMonitorQestnAns`
    - **Tables:** `ADM.TCOMMUNICATION`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /custQestnAns/saveBdMonitor
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.saveBdMonitor`
  - **Query:** `selectBdMonitorInf`
    - **Tables:** `ADM.TPGMMONITOR`
  - **Query:** `insertBdMonitorInf`
    - **Tables:** `ADM.TPGMMONITOR`
  - **Query:** `updateBdMonitorInf`
    - **Tables:** `ADM.TPGMMONITOR`

Loaded cached credentials.
### POST /vocmng/voc/saveCustCounReqt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.saveCustCounReqt`
  - **Query:** `srMapper.updateCustCounReqt`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /custQestnAns/retrieveCustQestnAns
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.retrieveCustQestnAns`
  - **Query:** `selectCustQestnAns`
    - **Tables:** `ADM.TCOMMUNICATION`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TITEMCHNL`, `ADM.TPERINFO`, `ADM.TMD`, `ADM.TDEPT`, `ADM.TSTRUCTUREMON`, `ADM.TMDLINK`, `ADM.TSTRUCTURE`, `ADM.TCOMMUNICATION_LOG`

Loaded cached credentials.
### GET /custQestnAns/retrieveNewCommunications
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.CustQestnAnsService.retrieveNewCommunications`
  - **Query:** `selectNewCommunications`
    - **Tables:** `ADM.TCOMMUNICATION`, `ADM.TPERINFO`, `ADM.TITEM`

Loaded cached credentials.
### GET /atenAgd/retrieveHolidaySpecDeliv
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.retrieveHolidaySpecDeliv`
  - **Query:** `selectRetrieveHolidaySpecDeliv`
    - **Tables:** `ADM.TITEMCHNL`, `ADM.TCODE`

Loaded cached credentials.
### GET /atenAgd/retrieveAtenAgdPopup
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.retrieveAtenAgdPopup`
  - **Tables:** `ADM.TDELIVCST`, `ADM.TITEM`, `ADM.TITEMADD`, `ADM.TITEMPOINT`


Loaded cached credentials.
### GET /atenAgd/retrieveItem
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.retrieveItem`
  - **Tables:** `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TITEMKINDS`, `ADM.TMDLINK`, `ADM.TPERINFO`, `ADM.TITEMVEN`

Loaded cached credentials.
### GET /atenAgd/retrieveItemAtenAgd
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.retrieveItemAtenAgd`
  - **Query:** `selectItemAtenAgd`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMPOINT`

Loaded cached credentials.
### POST /atenAgd/saveItemAtenAgd
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.saveItemAtenAgd`
  - **Query:** `insertItemAtenAgd`
    - **Tables:** `ADM.TITEMPOINT`
  - **Query:** `updateItemAtenAgd`
    - **Tables:** `ADM.TITEMPOINT`
  - **Query:** `insertItemAtenAgdHistory`
    - **Tables:** `ADM.TITEMPOINTHIST`, `ADM.TITEMPOINT`

Loaded cached credentials.
### POST /atenAgd/copyItemAtenAgd
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.copyItemAtenAgd`
  - **Query:** `ItemAtenAgdMapper.selectItemAtenAgd`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMPOINT`
  - **Query:** `ItemAtenAgdMapper.insertItemAtenAgd`
    - **Tables:** `ADM.TITEMPOINT`
  - **Query:** `ItemAtenAgdMapper.insertItemAtenAgdHistory`
    - **Tables:** `ADM.TITEMPOINTHIST`, `ADM.TITEMPOINT`

Loaded cached credentials.
### GET /atenAgd/retrieveItembyAtenAgdSttus
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ItemAtenAgdService.retrieveItembyAtenAgdSttus`
  - **Query:** `selectItembyAtenAgdSttus`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMPOINT`, `ADM.TITEMVEN`, `ADM.TITEMKINDS`, `ADM.TMD`

Loaded cached credentials.
### GET /sczone/retrieveScZoneStat
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ScZoneService.retrieveScZoneStat`
  - **Query:** `selectScZoneStat`
    - **Tables:** `ADM.TSCZONE`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TWEBEVENTM`, `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /vocmng/voc/retrieveVocInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocInf`
  - **Query:** `vocMapper.selectVocInfo`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### GET /sczone/retrieveScZoneDetail
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ScZoneService.retrieveScZoneDetail`
  - **Tables:** `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### POST /sczone/saveScZoneStat
- **Service:** `cj.bts.or.srm.domains.rsponsknwldg.service.ScZoneService.saveScZoneStat`
  - **Tables:** `ADM.TSCZONE`

Loaded cached credentials.
### GET /scorga/retrieveSCBizwrkProcGnrlz
- **Service:** `cj.bts.or.srm.domains.mypage.service.ScOrgaService.retrieveSCBizwrkProcGnrlz`
  - **Query:** `selectSCBizwrkProcGnrlz`
    - **Tables:** `ADM.TSCVOCTOTDSUM`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TCODE`, `ADM.TCOPY`

Loaded cached credentials.
### GET /scorga/retrieveSCBizwrkProcDtby
- **Service:** `cj.bts.or.srm.domains.mypage.service.ScOrgaService.retrieveSCBizwrkProcDtby`
  - **Query:** `selectSCBizwrkProcDtby`
    - **Tables:** `ADM.TSCVOCTOTDSUM`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TCODE`, `ADM.TCOPY`

Loaded cached credentials.
### GET /scorga/retrieveScOrdRcptSttus
- **Service:** `cj.bts.or.srm.domains.mypage.service.ScOrgaService.retrieveScOrdRcptSttus`
  - **Query:** `selectScOrdRcptSttus`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TORDER`

Loaded cached credentials.
### GET /scorga/retrieveWrkpartCdByUserId
- **Service:** `cj.bts.or.srm.domains.mypage.service.ScOrgaService.retrieveWrkpartCdByUserId`
  - **Query:** `selectBasisScInfo`
    - **Tables:** `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOMPANY`, `ADM.TSCWORKPARTHIST`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCrtfdocIssueReqHist
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCrtfdocIssueReqList`
  - **Query:** `selectCrtfdocIssueMngList`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TCERTREQ`, `ADM.TORDERTAXBIZR`, `ADM.TORDERDTAX`, `ADM.FILE_METADATA`, `ADM.TSAVEAMTREM`, `ADM.TPAMREM`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TINAMT`
  - **Query:** `selectCrtfdocIssueReqExpFormsList`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCUSTOMER`, `ADM.TCODE`
  - **Query:** `selectCrtfdocIssueReqEtcList`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TCBCODE`, `ADM.TSAVEAMTREM`, `ADM.TPAMREM`, `ADM.TCUSTOMER`
  - **Query:** `selectCrtfdocIssueReqNewList`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TORDERDTL`, `ADM.TCBCODE`, `ADM.TCUSTOMER`, `ADM.TCERTREQ`, `ADM.TORDERTAXBIZR`, `ADM.TORDERTAXBIZR_HIS`, `ADM.TORDERDTAX`, `ADM.TITEM`, `ADM.TINAMT`, `ADM.TPRIZECUST`, `ADM.TORDERPROC`
  - **Query:** `selectCrtfdocIssueReqReList`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TCERTREQ`, `ADM.TORDERTAXBIZR`, `ADM.TORDERTAXBIZR_HIS`, `ADM.TORDERDTAX`, `ADM.FILE_METADATA`, `ADM.TORDERITEM`, `ADM.TCUSTOMER`, `ADM.TINAMT`, `ADM.TORDERPROC`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCertificateFormEtc
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCertificateFormEtc`
  - **Query:** `selectCertificateFormEtc`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TCHANNEL`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /crtfdocmng/crtfdocIssue/saveCrtfdocSnddeliReq
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.saveCrtfdocSnddeliReq`
  - **Query:** `selectGenCrtfdocIssueReqNo`
    - **Tables:** `DUAL`
  - **Query:** `insertCrtfdocIssueReq`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectOrderTaxCustRequest`
    - **Tables:** `ADM.TORDERTAXCUST`
  - **Query:** `insertOrderTaxCustRequest`
    - **Tables:** `ADM.TORDERTAXCUST`
  - **Query:** `selectOrderTaxBizr`
    - **Tables:** `ADM.TORDERTAXBIZR`
  - **Query:** `selectTCode`
    - **Tables:** `ADM.TCODE`
  - **Query:** `selectVocCustOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`, `ADM.TLOGIHDAY`
  - **Query:** `selectVocItemInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMKINDS`

Loaded cached credentials.
### POST /crtfdocmng/crtfdocIssue/saveCrtfdocRePrint
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.saveCrtfdocRePrint`
  - **Query:** `updateCrtfdocIssueInf`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectTCode`
    - **Tables:** `ADM.TCODE`
  - **Query:** `selectVocCustOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`, `ADM.TLOGIHDAY`
  - **Query:** `selectVocItemInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /vocmng/voc/retrieveCustVocHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveCustVocHist`
  - **Query:** `vocJMapper.selectCustVocHist`
    - **Tables:** `ADM.TVOC`, `ADM.TCONTACT`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TSR`, `ADM.TCERTREQ`

Loaded cached credentials.
### POST /crtfdocmng/crtfdocIssue/saveCrtfdocReqCncl
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.saveCrtfdocReqCncl`
  - **Mapper:** `updateCrtfdocIssueInf`
    - **Tables:** `ADM.TCERTREQ`
  - **Mapper:** `selectTCode`
    - **Tables:** `ADM.TCODE`
  - **Mapper:** `selectVocCustOrd`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TCUSTOMER`, `ADM.TITEMKINDS`, `ADM.TLOGIHDAY`
  - **Mapper:** `selectVocItemInfo`
    - **Tables:** `ADM.TITEM`, `ADM.TITEMKINDS`

Loaded cached credentials.
### POST /crtfdocmng/crtfdocIssue/saveCrtfdocIssue
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.saveCrtfdocIssue`
  - **Query:** `selectGenCrtfdocIssueNo`
    - **Tables:** `DUAL`
  - **Query:** `selectContentsGetIssueNo`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectCertificateInfo`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TCHANNEL`, `ADM.TPERINFO`
  - **Query:** `updateCrtfdocIssueInf`
    - **Tables:** `ADM.TCERTREQ`

Loaded cached credentials.
### POST /crtfdocmng/crtfdocIssue/saveCrtfdocEmailIssue
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.saveCrtfdocEmailIssue`
  - **Query:** `selectGenCrtfdocIssueNo`
    - **Tables:** `DUAL`
  - **Query:** `selectContentsGetIssueNo`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectCertificateInfo`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TCHANNEL`, `ADM.TPERINFO`
  - **Query:** `updateCrtfdocIssueInf`
    - **Tables:** `ADM.TCERTREQ`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCustCertReqHits
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCustCertReqHits`
  - **Query:** `selectCustCertReqHits`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCODE`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveOrdItemSeq
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveOrdItemSeq`
  - **Query:** `selectOrdItemSeq`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TORDERDTL`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCertReqCustInfo
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCertReqCustInfo`
  - **Query:** `selectCertReqCustInfo`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TCERTREQ`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCertReqOrdCustInfo
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCertReqOrdCustInfo`
  - **Query:** `selectCertReqOrdCustInfo`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/retrieveCrtfdocProcSttus
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.retrieveCrtfdocProcSttus`
  - **Query:** `selectCrtfdocProcSttus`
    - **Tables:** `ADM.TCODE`, `ADM.TCERTREQ`, `ADM.TORDERDTL`

Loaded cached credentials.
### GET /crtfdocmng/crtfdocIssue/testSeq
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.CrtfdocIssueService.testSeq`
  - **Query:** `selectGenCrtfdocIssueReqNo`
    - **Tables:** `DUAL`
  - **Query:** `selectGenCrtfdocIssueReqNo2`
    - **Tables:** `DUAL`


Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/issueOutslTaxBilldoc
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.issueOutslTaxBilldoc`
  - **Query:** `selectRetngOrdCcnt`
    - **Tables:** `ADM.TORDERDTL`
  - **Query:** `selectTaxBillTaxNumber`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `insertTaxBilldocIssReq`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TCODE`
  - **Query:** `insertTaxBilldocIssReqDtl`
    - **Tables:** `ADM.TORDERTAXREQDTL`
  - **Query:** `selectGenTaxBilldocIssReqSeq`
    - **Tables:** `DUAL`, `ADM.QCERTISSUESEQ_NO`
  - **Query:** `insertTaxBilldocIss`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TCBCODE`, `ADM.TORDERITEM`
  - **Query:** `updateCrtfdocIssueReq`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectCashReceiptRequest`
    - **Tables:** `ADM.TORDERDTAX`
  - **Query:** `selectCashReceiptSend`
    - **Tables:** `ADM.TORDERDTAX`
  - **Query:** `selectCashReceiptCancelAmt`
    - **Tables:** `ADM.TINAMT`, `ADM.TCBCODE`, `ADM.TORDERDTL`
  - **Query:** `updateCashReceiptCancel`
    - **Tables:** `ADM.TORDERDTAX`
  - **Query:** `selectCashReceiptCancelRequestAmt`
    - **Tables:** `ADM.TORDERDTAX`
  - **Query:** `insertCashReceiptCancelOrNew`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERITEM`, `ADM.TCBCODE`
  - **Query:** `selectOrderCashCalVat`
    - **Tables:** `ADM.TORDERDTL`


Loaded cached credentials.
### GET /vocmng/voc/retrieveCustVocHistPaging
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveCustVocHistPaging`
  - **Query:** `vocJMapper.selectCustVocHistPaging`
    - **Tables:** `ADM.TVOC`, `ADM.TCONTACT`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TSR`, `ADM.TCERTREQ`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveOutslTaxBilldocIssTgt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveOutslTaxBilldocIssTgt`
  - **Query:** `selectPackItemDinslOutslTaxBilldocIssTgt`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXBIZR`, `ADM.TORDERITEM`, `ADM.TCBCODE`, `ADM.TORDERDTAX`, `ADM.TORDERTAXREQDTL`, `ADM.TINAMT`, `ADM.TORDERCOST`, `ADM.TCUSTOMER`
  - **Query:** `selectPackItemCnsgnOutslTaxBilldocIssTgt`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXBIZR`, `ADM.TORDERITEM`, `ADM.TVENDOR`, `ADM.TCBCODE`, `ADM.TORDERDTAX`, `ADM.TORDERTAXREQDTL`, `ADM.TINAMT`, `ADM.TORDERCOST`, `ADM.TCUSTOMER`
  - **Query:** `selectDinslOutslTaxBilldocIssTgt`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXBIZR`, `ADM.TORDERITEM`, `ADM.TORDERDTAX`, `ADM.TORDERTAXREQDTL`, `ADM.TINAMT`, `ADM.TORDERCOST`, `ADM.TCUSTOMER`, `ADM.TCBCODE`
  - **Query:** `selectCnsgnOutslTaxBilldocIssTgt`
    - **Tables:** (정보 없음)

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/issueRetngTaxBilldoc
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.issueRetngTaxBilldoc`
  - **Query:** `selectDinslRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`
  - **Query:** `selectCnsgnRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`
  - **Query:** `selectTaxBillTaxNumber`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `selectTaxBilldocIssBizusrNum`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERTAXBIZR`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `insertDinslRetngTaxBilldocIssReq`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERDTAX`, `ADM.TORDERTAXBIZR`, `ADM.TORDERDTL`, `ADM.TCERTREQ`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `selectTaxBilldocIssVendorNum`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TVENDOR`, `ADM.TVENPLACE`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `insertDinslRetngTaxBilldocVendorIssReq`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERDTAX`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TORDERDTL`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TVENDOR`, `ADM.TVENPLACE`
  - **Query:** `insertDinslRetngTaxBilldocIssReqDtl`
    - **Tables:** `ADM.TORDERTAXREQDTL`
  - **Query:** `selectGenTaxBilldocIssReqSeq`
    - **Tables:** `DUAL`, `ADM.QCERTISSUESEQ_NO`
  - **Query:** `insertRetngTaxBilldocIss`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TCBCODE`
  - **Query:** `insertCnsgnRetngTaxBilldocIssReq`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERDTAX`, `ADM.TORDERTAXBIZR`, `ADM.TORDERDTL`, `ADM.TCERTREQ`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
  - **Query:** `insertCnsgnRetngTaxBilldocVendorIssReq`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERDTAX`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TORDERDTL`, `ADM.TCODE`, `ADM.TCBCODE`, `ADM.TVENDOR`, `ADM.TVENPLACE`
  - **Query:** `insertCnsgnRetngTaxBilldocIssReqDtl`
    - **Tables:** `ADM.TORDERTAXREQDTL`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveRetngTaxBilldocIssTgt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveRetngTaxBilldocIssTgt`
  - **Query:** `selectRetngTaxBilldocHistStr`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERTAXREQ`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERDTL`, `ADM.TORDERITEM`, `ADM.TUSERS`
  - **Query:** `selectRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERTAXREQ`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERDTL`, `ADM.TORDERITEM`, `ADM.TUSERS`
  - **Query:** `selectDinslRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`
  - **Query:** `selectCnsgnRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveRetngTaxBilldocIssDtl
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveRetngTaxBilldocIssDtl`
  - **Query:** `selectDinslRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`
  - **Query:** `selectCnsgnRetngTaxBilldocHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERTAXREQ`, `ADM.TCBCODE`


Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/saveTaxBilldocIssueReSend
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.saveTaxBilldocIssueReSend`
  - **Query:** `updateTaxBilldocIssReqReSend`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `updateTaxBilldocIssReqDtlReSend`
    - **Tables:** `ADM.TORDERTAXREQDTL`
  - **Query:** `updateTaxBilldocIssReSend`
    - **Tables:** `ADM.TORDERDTAX`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBilldocBndePrt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBilldocBndePrt`
  - **Query:** `selectTaxBilldocBndePrt`
    - **Tables:** `ADM.tordertaxreqdtl`, `ADM.TORDERTAXREQ`, `ADM.TORDER`, `ADM.TORDERDTAX`, `ADM.TUSERS`, `ADM.TORDERDTL`, `ADM.TCBCODE`

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/excelTaxBilldocBndePrt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.excelTaxBilldocBndePrt`
  - **Query:** `selectTaxBilldocBndePrtExcel`
    - **Tables:** `ADM.tordertaxreqdtl`, `ADM.TORDERTAXREQ`, `ADM.TORDER`, `ADM.TORDERDTAX`, `ADM.TUSERS`, `ADM.TORDERDTL`, `ADM.TCBCODE`

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/issueCashRcipt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.issueCashRcipt`
  - **Query:** `selectGenTaxBilldocIssReqSeq`
    - **Tables:** `DUAL`, `ADM.QCERTISSUESEQ_NO`
  - **Query:** `insertTaxBilldocIss`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TCBCODE`, `ADM.TORDERITEM`

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/modifyCashRcipt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.modifyCashRcipt`
  - **Query:** `updateCashRcipt`
    - **Tables:** `ADM.TORDERDTAX`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveEtcTaxBilldocIssueTgt
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveEtcTaxBilldocIssueTgt`
  - **Query:** `selectEtcTaxBilldocIssueTgt`
    - **Tables:** `ADM.TCERTREQ`, `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TITEM`, `ADM.TORDERITEM`, `ADM.TORDERTAXBIZR`, `ADM.TORDERTAXBIZR_HIS`, `ADM.TORDERPROC`, `ADM.TORDERDTAX`

Loaded cached credentials.
### POST /vocmng/voc/saveReVoc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.saveReVoc`
  - **Query:** `vocMapper.insertReVoc`
    - **Tables:** `ADM.TVOC`
  - **Query:** `vocMapper.updateProcCdReVoc`
    - **Tables:** `ADM.TVOC`


Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBilldocBcnc
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBilldocBcnc`
  - **Query:** `selectTaxBilldocBcnc`
    - **Tables:** `ADM.TVENDOR`, `ADM.TVENPLACE`, `ADM.TORDERTAXBIZR`, `ADM.TUSERS`

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/saveTaxBilldocBcnc
-   **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.saveTaxBilldocBcnc`
    -   **Query:** `selectOrderTaxBizr`
        -   **Tables:** `ADM.TORDERTAXBIZR`
    -   **Query:** `updateTaxBilldocBcnc`
        -   **Tables:** `ADM.TORDERTAXBIZR`
    -   **Query:** `insertTaxBilldocBcnc`
        -   **Tables:** `ADM.TORDERTAXBIZR`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBilldocBcncHist
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBilldocBcncHist`
  - **Query:** `selectTaxBilldocBcncHist`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERTAXREQ`, `ADM.TORDERTAXREQDTL`, `ADM.TORDERDTL`, `ADM.TORDERITEM`, `ADM.TUSERS`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveOrderTaxBizrList
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveOrderTaxBizrList`
  - **Query:** `selectOrderTaxBizrList`
    - **Tables:** `ADM.TORDERTAXCUST`, `ADM.TORDERTAXBIZR`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveOrderTaxBizr2List
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveOrderTaxBizr2List`
  - **Query:** `selectOrderTaxBizr2List`
    - **Tables:** `ADM.TORDERTAXCUST`, `ADM.TORDERTAXBIZR`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBillCorpInfo
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBillCorpInfo`
  - **Query:** `selectTaxBillCorpInfo`
    - **Tables:** `ADM.TORDERTAXCUST`, `ADM.TORDERTAXBIZR`, `ADM.TCUSTOMER`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveSaleTaxBillIssueDate
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveSaleTaxBillIssueDate`
  - **Query:** `selectSaleTaxBillIssueDate`
    - **Tables:** `ADM.TORDERPROC`

Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/saveTaxBilldocIssQty
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.saveTaxBilldocIssQty`
  - **Query:** `updateTaxBilldocIssQty`
    - **Tables:** `ADM.TORDERDTAX`


Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBillCustInfo
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBillCustInfo`
  - **Query:** `selectTaxBillCustInfoByVenNm`
    - **Tables:** `ADM.TORDERTAXBIZR`, `ADM.TORDERTAXCUST`, `ADM.TCUSTADDR`, `ADM.TCUSTOMER`
  - **Query:** `selectTaxBillCustInfoByTelNo`
    - **Tables:** `ADM.TCUSTADDR`, `ADM.TCUSTOMER`
  - **Query:** `selectTaxBillCustInfo`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TCUSTADDR`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBillDelMasters
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBillDelMasters`
  - **Query:** `selectTaxBillDelMasters`
    - **Tables:** `ADM.TORDERDTAX`, `ADM.TORDERTAXREQ`, `ADM.TORDER`, `ADM.TORDERTAXBIZR`
  - **Query:** `selectTaxBillDelDetails`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERTAXREQDTL`

Loaded cached credentials.
### GET /vocmng/voc/retrieveSimiDeptVocAssignRoles
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveSimiDeptVocAssignRoles`
  - **Query:** `assignroleMapper.selectSimiDeptVocAssignRoles`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TPERINFO`
  - **Query:** `assignroleMapper.selectSimiAuth`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /crtfdocmng/taxBilldocIss/retrieveTaxBillDelDetails
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.retrieveTaxBillDelDetails`
  - **Query:** `selectTaxBillDelDetails`
    - **Tables:** `ADM.TORDERTAXREQ`, `ADM.TORDERTAXREQDTL`


Loaded cached credentials.
### POST /crtfdocmng/taxBilldocIss/saveTaxBilldocDelMod
- **Service:** `cj.bts.or.srm.domains.crtfdocmng.service.TaxBilldocIssService.saveTaxBilldocDelMod`
  - **Query:** `updateTaxBilldocIssReqCncl`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `updateTaxBilldocIssReqDtlCncl`
    - **Tables:** `ADM.TORDERTAXREQDTL`
  - **Query:** `updateTaxBilldocIssCncl`
    - **Tables:** `ADM.TORDERDTAX`
  - **Query:** `updateCrtfdocIssueCncl`
    - **Tables:** `ADM.TCERTREQ`
  - **Query:** `selectTaxBillTaxNumber`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `insertTaxBilldocIssReqCncl`
    - **Tables:** `ADM.TORDERTAXREQ`
  - **Query:** `insertTaxBilldocIssReqDtlCncl`
    - **Tables:** `ADM.TORDERTAXREQDTL`
  - **Query:** `selectGenTaxBilldocIssReqSeq`
    - **Tables:** `DUAL`, `ADM.QCERTISSUESEQ_NO`
  - **Query:** `insertTaxBilldocIssCncl`
    - **Tables:** `ADM.TORDERDTAX`

Loaded cached credentials.
### GET /survey/qna/retrieveSurveyQestnAns
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyQestnAnsService.retrieveSurveyQestnAns`
  - **Query:** `selectSurveyQuestionList`
    - **Tables:** `ADM.TSURVEYQUEST`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TOBCUST`, `ADM.TORDERDTL`, `ADM.TITEM`
  - **Query:** `selectSurveyAnswerList`
    - **Tables:** `ADM.TSURVEYQUESTANS`
  - **Query:** `selectCustomerSurveyAnswerList`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TSURVEYQUESTANS`

Loaded cached credentials.
### GET /survey/crindexSurveyTgtItem/retrieveCRindexSurveyTgtItem
- **Service:** `cj.bts.or.srm.domains.survey.service.CrindexSurveyTgtItemService.retrieveCRindexSurveyTgtItem`
  - **Query:** `selectCRindexSurveyTgtItem`
    - **Tables:** `ADM.TQUESTCRIOBJ`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TBRAND`, `ADM.TMD`, `ADM.TQUESTCRIOBJORDER`, `ADM.TCUSTMAGREE`, `DUAL`

Loaded cached credentials.
### GET /survey/crindexSurveyTgtItem/retrieveCRindexSurveyStreRst
- **Service:** `cj.bts.or.srm.domains.survey.service.CrindexSurveyTgtItemService.retrieveCRindexSurveyStreRst`
  - **Query:** `selectCRindexSurveyStreRst`
    - **Tables:** `ADM.TQUESTCRI`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TSTRUCTURE`, `ADM.TBRAND`, `ADM.TMD`

Loaded cached credentials.
### GET /survey/crindexSurveyTgtItem/retrieveCRindexSurveyCriNoList
- **Service:** `cj.bts.or.srm.domains.survey.service.CrindexSurveyTgtItemService.retrieveCRindexSurveyCriNoList`
  - **Query:** `selectCRindexSurveyCriNoList`
    - **Tables:** `ADM.TQUESTCRI`

Loaded cached credentials.
### POST /survey/crindexSurveyTgtItem/registerCRindexSurveyTgtItem
- **Service:** `cj.bts.or.srm.domains.survey.service.CrindexSurveyTgtItemService.registerCRindexSurveyTgtItem`
  - **Query:** `selectNewSurveyCriNo`
    - **Tables:** `ADM.TQUESTCRI`
  - **Query:** `selectNewSurveyCriSeq`
    - **Tables:** `ADM.TQUESTCRI`
  - **Query:** `selectChkSurveyCustCnt`
    - **Tables:** `ADM.TQUESTCRIOBJORDER`, `ADM.TQUESTCRICUST`
  - **Query:** `insertSurveyCri`
    - **Tables:** `ADM.TQUESTCRI`
  - **Query:** `insertSurveyCriCust`
    - **Tables:** `ADM.TQUESTCRICUST`, `ADM.TQUESTCRIOBJORDER`
  - **Query:** `updateSurveyCri`
    - **Tables:** `ADM.TQUESTCRI`
  - **Query:** `updateSurveyCriCust`
    - **Tables:** `ADM.TQUESTCRICUST`

Loaded cached credentials.
### GET /survey/progsttus/retrieveSurveyProgSttus
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyProgSttusMngService.retrieveSurveyProgSttus`
  - **Query:** `selectSurveyProgSttus`
    - **Tables:** `ADM.TSURVEY`, `ADM.TSURVEYSEG`, `ADM.TCODE`, `ADM.TSURVEYCUSTANS`, `ADM.TOBCUST`, `ADM.TOB`, `ADM.TOFFER`, `ADM.TPERINFO`, `ADM.TSURVEYSEGOFFER`, `ADM.TPROM`, `ADM.TOBSEG`, `ADM.TOBPAYOFFER`

Loaded cached credentials.
### GET /survey/ob/retrieveSurveyObLst
- **Service:** `cj.bts.or.srm.domains.ob.service.ObTgtusrService.retrieveObTgtusrCustbyHist`
  - **Tables:** `ADM.TOBCUST`
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyObService.retrieveSurveyObCrteLst`
  - **Query:** `selectSurveyInfo`
    - **Tables:** `ADM.TSURVEYSEG`, `ADM.TSURVEY`, `ADM.TCODE`
  - **Query:** `selectSurveyOdList`
    - **Tables:** `ADM.TSURVEYSEG`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TCODE`
  - **Query:** `selectSurveyOdCnt`
    - **Tables:** `ADM.TOBSEG`

Loaded cached credentials.
### GET /survey/ob/retrieveSurveyObReqDtl
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.retrieveObProcTgtDtlHist`
  - **Tables:** `ADM.TOBCUST`, `ADM.TOBPROC`, `ADM.TOB`, `ADM.TOBSEG`, `ADM.TOBREASONCODE`, `ADM.TCODE`, `ADM.TCUSTOMER`, `ADM.TPERINFO`, `ADM.TCBCODE`, `ADM.TVENADDINFO`, `ADM.TB2BPRTNR`, `ADM.TOBCALLDTL`
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyObService.selectSurveyObReqDtl`
  - **Query:** `selectObCust`
    - **Tables:** `ADM.TOBCUST`, `ADM.TCUSTOMER`
  - **Query:** `selectSurveyObReq`
    - **Tables:** `ADM.TOBREQDTL`
  - **Query:** `selectSurveyObReqDtlList`
    - **Tables:** `ADM.TOBREASONADM`

Loaded cached credentials.
### POST /vocmng/voc/saveSimiDeptVoc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.saveSimiDeptVoc`
  - **Query:** `vocMapper.insertReVoc`
    - **Tables:** `ADM.TVOC`
  - **Query:** `vocMapper.updateProcCdReVoc`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### POST /survey/ob/saveSurveyObTgtusrExtrc
- **Service:** `cj.bts.or.srm.domains.ob.service.ObProcService.saveObProcByObObj`
  - **Tables:** `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyObService.saveSurveyObTgtusrExtrc`
  - **Query:** `modifySurveyObReq`
    - **Tables:** `ADM.TOBREQDTL`
  - **Query:** `insertSurveyObReq`
    - **Tables:** `ADM.TOBREQDTL`
  - **Query:** `modifySurveyObReqCd`
    - **Tables:** `ADM.TOBREASONADM`
  - **Query:** `selectSurveyObReq`
    - **Tables:** `ADM.TOBREQDTL`
  - **Query:** `insertSurveyObReqCd`
    - **Tables:** `ADM.TOBREASONADM`

Loaded cached credentials.
### GET /survey/ob/retrieveSurveySegObLst
- **Service:** `cj.bts.or.srm.domains.ob.service.ObObjMoCrtService.retrieveObSegs`
  - **Query:** `selectObSegs`
    - **Tables:** `ADM.TOBSEG`, `ADM.TPERINFO`
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyObService.retrieveSurveySegObLst`
  - **Query:** `selectSurveyOdList`
    - **Tables:** `ADM.TSURVEYSEG`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TCODE`
  - **Query:** `selectSurveyOdCnt`
    - **Tables:** `ADM.TOBSEG`

Loaded cached credentials.
### POST /survey/ob/saveServeyObReqCopy
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyObService.saveServeyObReqCopy`
  - **Query:** `modifySurveyObReqDtlCopy`
    - **Tables:** `ADM.TOBREQDTL`
  - **Query:** `insertSurveyObReqDtlCopy`
    - **Tables:** `ADM.TOBREQDTL`, `ADM.TOB`
  - **Query:** `modifySurveyObReqCdCopy`
    - **Tables:** `ADM.TOBREASONADM`
  - **Query:** `selectSurveyObReqDtlCdList`
    - **Tables:** `ADM.TOBREASONADM`
  - **Query:** `insertSurveyObReqCdCopy`
    - **Tables:** `ADM.TOBREASONADM`, `ADM.TOB`

Loaded cached credentials.
### GET /survey/custResSttus/retrieveSurveyCustResSttus
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyCustResSttusMngService.retrieveSurveyCustResSttus`
  - **Query:** `selectSurveyCustResSttus2`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurveyCustResSttus1`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSURVEYCUSTANS`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurveyCustResLst`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSURVEYCUSTANS`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurvey`
    - **Tables:** `ADM.TSURVEY`, `ADM.TCODE`, `ADM.TOB`
  - **Query:** `selectAge`
    - **Tables:** `ADM.TCODE`
  - **Query:** `selectCustAnsLst`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TSURVEYQUESTANS`, `ADM.TORDERDTL`, `ADM.TRECEIVER`


Loaded cached credentials.
### GET /survey/proc/retrieveSurveyProc
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyProcService.retrieveSurveyProc`
  - **Query:** `selectSegNoList`
    - **Tables:** `ADM.TOBCUST`, `ADM.TOBSEG`
  - **Query:** `selectSurvey`
    - **Tables:** `ADM.TSURVEY`, `ADM.TITEM`, `ADM.TCODE`, `ADM.TOB`
  - **Query:** `selectObCust`
    - **Tables:** `ADM.TOBCUST`, `ADM.TCUSTOMER`
  - **Query:** `selectSurveyQuestionList`
    - **Tables:** `ADM.TSURVEYQUEST`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TOBCUST`, `ADM.TORDERDTL`, `ADM.TITEM`
  - **Query:** `selectSurveyAnswerList`
    - **Tables:** `ADM.TSURVEYQUESTANS`
  - **Query:** `selectCustomerSurveyAnswerList`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TSURVEYQUESTANS`
  - **Query:** `selectSurveyProcessDetail`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TCUSTMAGREE`, `ADM.TOBAGREE`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `ADM.TCODE`, `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TOBCUST`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TCUSTADDR`
  - **Query:** `selectCustAddr`
    - **Tables:** `ADM.TCUSTADDR`, `ADM.TORDERDTL`, `ADM.TRECEIVER`

Loaded cached credentials.
### POST /survey/proc/saveSurveyProc
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyProcService.saveSurveyProc`
  - **Query:** `insertCustomerSurveyAnswer`
    - **Tables:** `ADM.TSURVEYCUSTANS`
  - **Query:** `modifyObObjByProc`
    - **Tables:** `ADM.TOBCUST`
  - **Query:** `getObSeg`
    - **Tables:** `ADM.TOBSEG`, `ADM.TPERINFO`
  - **Query:** `selectObSegs`
    - **Tables:** `ADM.TOBSEG`, `ADM.TPERINFO`
  - **Query:** `modifyObByProgCd`
    - **Tables:** `ADM.TOB`
  - **Query:** `updateSurveyProgCd`
    - **Tables:** `ADM.TSURVEY`
  - **Query:** `updateObSeg`
    - **Tables:** `ADM.TOBSEG`
  - **Query:** `getObAgree`
    - **Tables:** `ADM.TOBAGREE`
  - **Query:** `insertObAgree`
    - **Tables:** `ADM.TOBAGREE`

Loaded cached credentials.
### GET /survey/proc/retrieveSurveyQstitemResAgg
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyProcService.retrieveSurveyQstitemResAgg`
  - **Query:** `selectSurveyProgStep`
    - **Tables:** `ADM.TSURVEY`, `ADM.TCODE`
  - **Query:** `selectSurveyQstitemResCcnt`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TOBCUST`
  - **Query:** `selectSurveyQstitemResAgg`
    - **Tables:** `ADM.TSURVEYQUESTANS`, `ADM.TSURVEYCUSTANS`, `ADM.TCUSTOMER`, `ADM.TSEGCUST`, `ADM.TSEGCODE`

Loaded cached credentials.
### GET /survey/proc/retrieveSurveyQstitemResAnals
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyProcService.retrieveSurveyQstitemResAnals`
  - **Query:** `selectSurveyProgStep`
    - **Tables:** `ADM.TSURVEY`, `ADM.TCODE`
  - **Query:** `selectSurveyQstitemResCcnt`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TOBCUST`
  - **Query:** `selectSurveyQestnLst`
    - **Tables:** `ADM.TSURVEYQUEST`, `ADM.TSURVEY`, `ADM.TOB`, `ADM.TOBCUST`, `ADM.TORDERDTL`, `ADM.TITEM`

Loaded cached credentials.
### GET /survey/proc/retrieveSurveyCustResSttus
- **Service:** `cj.bts.or.srm.domains.survey.service.SurveyCustResSttusMngService.retrieveSurveyCustResSttus`
  - **Query:** `selectSurveyCustResSttus2`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurveyCustResSttus1`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSURVEYCUSTANS`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurveyCustResLst`
    - **Tables:** `ADM.TOB`, `ADM.TOBCUST`, `ADM.TCUSTOMER`, `ADM.TCUSTADDR`, `ADM.TSURVEYCUSTANS`, `ADM.TSEGCUST`, `ADM.TSEGCODE`, `DUAL`
  - **Query:** `selectSurvey`
    - **Tables:** `ADM.TSURVEY`, `ADM.TCODE`, `ADM.TOB`
  - **Query:** `selectAge`
    - **Tables:** `ADM.TCODE`
  - **Query:** `selectCustAnsLst`
    - **Tables:** `ADM.TSURVEYCUSTANS`, `ADM.TSURVEYQUESTANS`, `ADM.TORDERDTL`, `ADM.TRECEIVER`

Loaded cached credentials.
### GET /sci/token/generate
- **Service:** `cj.bts.or.srm.domains.realname.service.SciService.generateToken`
  - **Query:** `generateToken`
    - **Tables:** (No direct database interaction via provided mappers)

Loaded cached credentials.
### GET /vocmng/voc/retrieveDelicompWbTraceInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveDelicompWbTraceInfo`
  - **Query:** `orderdtlJMapper.selectDelicompWbTraceInfo`
    - **Tables:** `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`

Loaded cached credentials.
### GET /sci/token/revoke
- **Service:** `cj.bts.or.srm.domains.realname.service.SciService.revokeToken`
  - **Note:** Provided analysis does not show direct database table access for this service method. It likely performs an action without direct database interaction or interacts with an external system.

Loaded cached credentials.
### GET /sci/token/crypto
- **Service:** `cj.bts.or.srm.domains.realname.service.SciService.cryptoToken`
  - (No direct database queries found in the provided analysis for this service method.)

Loaded cached credentials.
### POST /sci/real-name/auth
- **Service:** `cj.bts.or.srm.domains.realname.service.SciService.auth`
  - **Note:** Based on the provided analysis, the methods called by `SciService.auth` (`generateCryptoToken`, `checkForeignerName`, `checkNationalName`) do not directly invoke any mappers listed in the "Mapper 전체 분석 결과". Therefore, no direct database table access is identified for this endpoint from the given data.

Loaded cached credentials.
### POST /real-name/authenticate
- **Service:** `cj.bts.or.srm.domains.realname.service.RealNameAuthService.process`
  - **Query:** `insertCustomerCertificationErrorLog`
    - **Tables:** `ADM.TCUSTCERT_ERROR`

Loaded cached credentials.
### GET /usermng/vdiauthmng/retrieveVdiUseAuthority
- **Service:** `cj.bts.or.srm.domains.usermng.service.VdiUseAuthorityManageService.retrieveVdiUseAuthority`
  - **Query:** `selectVdiUseAuthority`
    - **Tables:** `ADM.TORDCOUNVDICTI`

Loaded cached credentials.
### POST /usermng/vdiauthmng/saveVdiUseAuthority
- **Service:** `cj.bts.or.srm.domains.usermng.service.VdiUseAuthorityManageService.saveVdiUseAuthority`
  - **Query:** `selectVdiUseAuthority`
    - **Tables:** `ADM.TORDCOUNVDICTI`
  - **Query:** `updateVdiUseYn`
    - **Tables:** `ADM.TORDCOUNVDICTI`
  - **Query:** `insertVdiUseAuthority`
    - **Tables:** `ADM.TORDCOUNVDICTI`

Loaded cached credentials.
### POST /srmng/srerrmng/saveScErrMdt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.saveScErrMdt`
  - **Query:** `srerrorMapper.updateScErrMdt`
    - **Tables:** `ADM.TSRERROR`
  - **Query:** `srMapper.updateSrErrConfYn`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveIsExtPartnerRecvHpNoByOrdNo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveIsExtPartnerRecvHpNoByOrdNo`
  - **Query:** `orderdtlJMapper.selectIsExtPartnerRecvHpNoByOrdNo`
    - **Tables:** `ADM.TEXTPARTNER_ORDER`

Loaded cached credentials.
### GET /vocmng/voc/retrieveAssignScRole
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveAssignScRole`
  - **Query:** `assignroleMapper.selectAssignScRole`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TSCWORKPART`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /vocmng/voc/retrieveAsOrderProcEnableYn
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveAsOrderProcEnableYn`
  - **Query:** `orderdtlJMapper.selectAsOrderItemQty`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TITEM`, `ADM.TCHANNEL`
  - **Query:** `orderdtlJMapper.selectChkAsOrder`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TITEMCHNL`

Loaded cached credentials.
### GET /vocmng/voc/saveVocType
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.saveVocType`
  - **Query:** `vocMapper.updateVocType`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### GET /vocmng/voc/retrieveScZoneList
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveScZoneList`
  - **Query:** `sczoneJMapper.selectScZoneList`
    - **Tables:** `ADM.TSCZONE`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TSR`

Loaded cached credentials.
### POST /vocmng/voc/saveScZoneReg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.saveScZoneReg`
  - **Query:** `sczoneMapper.insertSczone`
    - **Tables:** `ADM.TSCZONE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveVocRcptSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocRcptSttus`
  - **Query:** `vocMapper.selectVocRcptSttus`
    - **Tables:** `ADM.TVOC`

Loaded cached credentials.
### GET /vocmng/voc/retrieveRelateDeptRefVocHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveRelateDeptRefVocHist`
  - **Query:** `vocJMapper.selectRelateDeptRefVoc`
    - **Tables:** `ADM.TSR`, `ADM.TVOC`, `ADM.TSEGCUST`, `ADM.TASSIGNROLE`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TORDERDTL`, `ADM.TITEMCHNL`, `ADM.TMDLINK`, `ADM.TCUSTOMER`, `ADM.TVOCCODE`, `ADM.TITEM`, `ADM.TVENDOR`, `ADM.TPERINFO`, `ADM.TCODE`, `ADM.TCHANNEL`

Loaded cached credentials.
### GET /vocmng/voc/retrieveRelateDeptRefVocDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveRelateDeptRefVocDtl`
  - **Query:** `vocJMapper.selectRelateDeptRefVocDtl`
    - **Tables:** `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### GET /vocmng/voc/retrieveProcAssignRoles
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveProcAssignRoles`
  - **Query:** `assignroleMapper.selectProcAssignRole`
    - **Tables:** `ADM.TASSIGNROLE`

Loaded cached credentials.
### GET /srmng/srerrmng/retrieveSrErrProcDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.retrieveSrErrProcDtl`
  - **Query:** `srerrorMapper.selectSrErrProcDtl`
    - **Tables:** `ADM.TSRERROR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveMediaNewsVocHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveMediaNewsVocHist`
  - **Query:** `vocJMapper.selectMediaNewsVocHist`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TSTRUCTUREMON`, `ADM.TREWARD`, `ADM.TCUSTOMER`, `ADM.TVENDOR`, `ADM.TCHANNEL`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveRealtmOrdSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveRealtmOrdSttus`
  - **Query:** `orderdtlJMapper.selectRealtmOrdSttus`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TCHANNEL`, `ADM.TVENDOR`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/voc/retrieveSrInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveSrInfo`
  - **Query:** `srMapper.selectSrInfo`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveTrubVoc
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveTrubVoc`
  - **Query:** `vocJMapper.selectTrubVoc`
    - **Tables:** `ADM.TVOC`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TITEMVEN`, `ADM.TCODE`, `ADM.TREWARD`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TSCWORKPART`, `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/voc/retrieveTrubVocDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveTrubVocDtl`
  - **Query:** `vocMapper.selectTrubVocDtl`
    - **Tables:** `ADM.TVOC`, `ADM.TSRPROC`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveIndivbySrDtbyProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveIndivbySrDtbyProcSttus`
  - **Query:** `srscdsumJMapper.selectIndivbySrDtbyProcSttus`
    - **Tables:** `ADM.TSRSCDSUM`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveCustCounReqtHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveCustCounReqtHist`
  - **Query:** `srJMapper.selectCustCounHistSttus`
    - **Tables:** `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TCUSTOMER`, `ADM.TITEM`, `ADM.TPERINFO`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TVOCCODE`, `ADM.TBRAND`, `ADM.TITEMKINDS`, `ADM.TCHANNEL`, `ADM.TCODE`, `ADM.TMDLINK`, `ADM.TGRPCOMP`, `ADM.TSEGCUST`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveScIndivbyProcCcnt
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveSCIndivbyProcCcnt`
  - **Query:** `srJMapper.selectSCIndivbyProcCcnt`
    - **Tables:** `ADM.TSR`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TVENDOR`, `ADM.TVOCCODE`, `ADM.TPERINFO`, `ADM.TASSIGNROLE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveCustCounReqtDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveCustCounReqtDtl`
  - **Query:** `srprocMapper.selectSrProcHistDtl`
    - **Tables:** `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveItembySrProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveItembySrProcSttus`
  - **Query:** `sritemsumMapper.selectDtbySrProcSttus`
    - **Tables:** `ADM.TSRITEMSUM`, `ADM.TVOCCODE`, `ADM.TCODE`
  - **Query:** `sritemsumMapper.selectVocClsbySrProcSttus`
    - **Tables:** `ADM.TSRITEMSUM`, `ADM.TVOCCODE`, `ADM.TCODE`
  - **Query:** `sritemsumMapper.selectItemClsbySrProcSttus`
    - **Tables:** `ADM.TSRITEMSUM`, `ADM.TVOCCODE`, `ADM.TITEMKINDS`, `ADM.TCODE`

Loaded cached credentials.
### POST /srmng/srerrmng/saveSrProcErr
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.saveSrProcErr`
  - **Query:** `srerrorMapper.insertSrErrMng`
    - **Tables:** `ADM.TSRERROR`
  - **Query:** `srMapper.updateSrErrReg`
    - **Tables:** `ADM.TSR`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveVenCompItembySrProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveVenCompItembySrProcSttus`
  - **Query:** `srJMapper.selectVenCompItembySrProcSttus`
    - **Tables:** `ADM.TSR`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TVOC`, `ADM.TVENDOR`, `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrievePeriodbySrProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrievePeriodbySrProcSttus`
  - **Query:** `srJMapper.selectPeriodbySrProcSttus`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveScAsgnProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveScAsgnProcSttus`
  - **Query:** `vocMapper.selectScAsgnProcSttus`
    - **Tables:** `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TVOC`, `ADM.TSR`, `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveSrClsItemgrpSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveSrClsItemgrpSttus`
  - **Query:** `vocMapper.selectSrClsItemgrpSttus`
    - **Tables:** `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TVOCCODE`, `ADM.TSCWORKPART`, `ADM.TVOC`, `ADM.TSR`


Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveBbsSrProcSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveBbsSrProcSttus`
  - **Query:** `mallboardMapper.selectBbsSrProcSttus`
    - **Tables:** `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TMALLBOARD`, `ADM.TBOARDSCASSIGN`, `ADM.TMALLBOARDPROC`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveIndivbySrProcSttus
-   **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveIndivbySrProcSttus`
    -   **Query:** `srscalldsumMapper.selectIndivbySrProcSttus1`
        -   **Tables:** `ADM.TSRSCALLDSUM`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`
    -   **Query:** `srscalldsumMapper.selectIndivbySrProcSttus2`
        -   **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TPERINFO`
    -   **Query:** `srscalldsumMapper.selectIndivbySrProcSttus3`
        -   **Tables:** `ADM.TSRCLSDSUM`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TVOCCODE`
    -   **Query:** `srscalldsumMapper.selectIndivbySrProcSttus4`
        -   **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TPERINFO`, `ADM.TVOCCODE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveIndivbySrClsbyDtl
-   **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveIndivbySrClsDtl`
    -   **Query:** `srJMapper.selectIndivbySrClsDtl1`
        -   **Tables:** `ADM.TSRSCALLDSUM`, `ADM.TDEPT`, `ADM.TWORKPART`
    -   **Query:** `srJMapper.selectIndivbySrClsDtl2`
        -   **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`
    -   **Query:** `srJMapper.selectIndivbySrClsDtl3`
        -   **Tables:** `ADM.TSRCLSDSUM`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TVOCCODE`
    -   **Query:** `srJMapper.selectIndivbySrClsDtl4`
        -   **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TVOCCODE`


Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveSCProcTmSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveSCProcTmSttus`
  - **Query:** `boardleadtimedsumMapper.selectOfcwrkTeambyProcVeSttus`
    - **Tables:** `ADM.TBOARDLEADTIMEDSUM`, `ADM.TSCWORKPART`, `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`
  - **Query:** `mallboardMapper.selectSCTeambyProcVeSttus`
    - **Tables:** `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TMALLBOARD`, `ADM.TMALLBOARDPROC`, `ADM.TBOARDSCASSIGN`, `ADM.TSCWORKPART`
  - **Query:** `mallboardMapper.selectInsBaseSCTeambyProcVeSttus`
    - **Tables:** `ADM.TDEPT`, `ADM.TWORKPART`, `ADM.TPERINFO`, `ADM.TMALLBOARD`, `ADM.TMALLBOARDPROC`, `ADM.TSCWORKPART`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveBbsDdProcrtAgg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveBbsDdProcrtAgg`
  - **Query:** `mallboardMapper.selectProcBaseBbsDdProcrtRslt`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TEXT_REQUESTION`, `ADM.TMALLBOARDPROC`
  - **Query:** `mallboardMapper.selectProcBaseBbsDdProcrtSum`
    - **Tables:** `ADM.TMALLBOARD`, `ADM.TEXT_REQUESTION`, `ADM.TMALLBOARDPROC`
  - **Query:** `mallboardMapper.selectInsBaseBbsDdProcrtRslt`
    - **Tables:** `ADM.TMALLBOARDPROC`, `ADM.TMALLBOARD`, `ADM.TEXT_REQUESTION`
  - **Query:** `mallboardMapper.selectInsBaseBbsDdProcrtSum`
    - **Tables:** `ADM.TMALLBOARDPROC`, `ADM.TMALLBOARD`, `ADM.TEXT_REQUESTION`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveSCVocTpSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveSCVocTpSttus`
  - **Query:** `vocJMapper.selectSCVocTpSttus`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TITEM`, `ADM.TSTRUCTUREMON`, `ADM.TVOCCODE`, `ADM.TPERINFO`, `ADM.TDEPT`, `ADM.TWORKPART`

Loaded cached credentials.
### GET /srmng/srerrmng/retrieveScErrBizwrkSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrErrMngService.retrieveScErrBizwrkSttus`
  - **Query:** `assignroleMapper.selectSearchSvYn`
    - **Tables:** `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`, `DUAL`
  - **Query:** `srerrorJMapper.selectScErrBizwrkSttus`
    - **Tables:** `ADM.TSRERROR`, `ADM.TSR`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TASSIGNROLE`, `ADM.TASSIGNSCROLE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveDelivAbtVocAnals
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveDelivAbtVocAnals`
  - **Query:** `vocJMapper.selectDelivAbtVocAnals`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`, `ADM.TVOCCODE`, `ADM.TBRANCHLOC`, `ADM.TPERINFO`, `ADM.TMD`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveSrRercptSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveSrRercptSttus`
  - **Query:** `srprocMapper.selectSrRercptSttus`
    - **Tables:** `ADM.TCUSTOMER`, `ADM.TSRPROC`, `ADM.TSR`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveRealtmVocRcptTpby
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveRealtmVocRcptTpby`
  - **Query:** `srJMapper.selectRealtmVocRcptTpby`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TVOCCODE`, `ADM.TVOC`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TMDLINK`, `ADM.TPERINFO`, `ADM.TCODE`
  - **Query:** `srJMapper.selectItembyVocRcptTp`
    - **Tables:** `ADM.TSR`, `ADM.TSRPROC`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TITEMKINDS`, `ADM.TCHANNEL`, `ADM.TVOC`, `ADM.TCODE`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TMDLINK`, `ADM.TPERINFO`
  - **Query:** `srJMapper.selectRcptRsnVocHist`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TMDLINK`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCODE`, `ADM.TITEMKINDS`

Loaded cached credentials.
### POST /vocmng/IbSttusMng/excelRealtmVocRcptTpby
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.excelRealtmVocRcptTpby`
  - **Query:** `srJMapper.selectRcptRsnVocHistExcel`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TMDLINK`, `ADM.TPERINFO`, `ADM.TSCWORKPART`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCODE`, `ADM.TITEMKINDS`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveDelivAbtVocAnalsAgg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.SrService.retrieveDelivAbtVocAnalsAgg`
  - **Query:** `srJMapper.selectItembyDelivAnalsAgg`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TITEMKINDS`, `ADM.TCODE`, `ADM.TMD`, `ADM.TVENDOR`
  - **Query:** `srJMapper.selectBizdivDelivAnalsAgg`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TSTRUCTURE`
  - **Query:** `srJMapper.selectDelivOwnDelivAnalsAgg`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`
  - **Query:** `srJMapper.selectDelihomeCompDelivAnalsAgg`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`
  - **Query:** `srJMapper.selectVenCompDelivAnalsAgg`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TORDERPROC`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TVENDOR`


Loaded cached credentials.
### POST /vocmng/IbSttusMng/excelDelivAbtVocAnalsAgg
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.excelDelivAbtVocAnals`
  - **Query:** `vocJMapper.selectDelivAbtVocAnalsExcel`
    - **Tables:** `ADM.TVOC`, `ADM.TSR`, `ADM.TORDERDTL`, `ADM.TRECEIVER`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSTRUCTUREMON`, `ADM.TWBILLDTL`, `ADM.TWBILL`, `ADM.TCODE`, `ADM.TVOCCODE`, `ADM.TBRANCHLOC`, `ADM.TPERINFO`, `ADM.TMD`, `ADM.TSRPROC`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrieveRealtmCallSttus
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrieveRealtmCallSttus`
  - **Query:** `realtimecallMapper.selectRealTimeItmCall`
    - **Tables:** `ADM.TORDERDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TITEMCHNL`, `ADM.TVOC`, `ADM.TSR`, `ADM.TCBCODE`, `ADM.TITEM`
  - **Query:** `realtimecallMapper.selectRealTimeReason`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TORDERDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TITEMCHNL`, `ADM.TVOC`, `ADM.TSR`, `ADM.TCBCODE`
  - **Query:** `realtimecallMapper.selectRealTimevoc`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TORDERDTL`, `ADM.TBDSCHEDTL`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TBDSCHEDTL_BEF`, `ADM.TVOC`, `ADM.TSR`
  - **Query:** `realtimecallMapper.selectRealTimeitem`
    - **Tables:** `ADM.TITEM`, `ADM.TORDERDTL`, `ADM.TBDSCHEDTL`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TBDSCHEDTL_BEF`
  - **Query:** `realtimecallMapper.selectRealTimeConsvoc`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TVOC`, `ADM.TSR`, `ADM.TBDSCHEDTL`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TBDSCHEDTL_BEF`, `ADM.TORDERDTL`
  - **Query:** `realtimecallMapper.selectRealTimeConsitem`
    - **Tables:** `ADM.TITEM`, `ADM.TVOC`, `ADM.TSR`, `ADM.TBDSCHEDTL`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TBDSCHEDTL_BEF`, `ADM.TORDERDTL`
  - **Query:** `realtimecallMapper.selectRealTimeOrdConsvoc`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TVOC`, `ADM.TSR`, `ADM.TBDSCHEDTL`, `ADM.TITEM`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TBDSCHEDTL_BEF`, `ADM.TORDERDTL`, `ADM.TCOUNORDER`
  - **Query:** `realtimecallMapper.selectRealTimeOrdConsitem`
    - **Tables:** `ADM.TITEM`, `ADM.TVOC`, `ADM.TSR`, `ADM.TBDSCHEDTL`, `ADM.TITEMCHNL`, `ADM.TSBSCHEDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TBDSCHEDTL_BEF`, `ADM.TORDERDTL`, `ADM.TCOUNORDER`
  - **Query:** `realtimecallMapper.selectRealTimeOrdGrp`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TORDERDTL`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TVOC`
  - **Query:** `realtimecallMapper.selectRealTimeArsGrp`
    - **Tables:** `ADM.TREALDATETIME`, `ADM.TORDERDTL`, `ADM.TCOUNORDER`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`
  - **Query:** `realtimecallMapper.selectRealTimeTop5`
    - **Tables:** `ADM.TITEM`, `ADM.TORDERDTL`, `ADM.TSTRUCTUREMON`, `ADM.TSCWORKPART`, `ADM.TPERINFO`, `ADM.TWORKPART`, `ADM.TDEPT`, `ADM.TCOUNORDER`, `ADM.TITEMCHNL`, `ADM.TVOC`, `ADM.TSR`, `ADM.TCBCODE`

Loaded cached credentials.
### GET /vocmng/IbSttusMng/retrievePgmCallTmHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.retrievePgmCallTmHist`
  - **Query:** `cmscallJMapper.selectPgmCallTmHist`
    - **Tables:** `ADM.TPROGRAM`, `ADM.TBDSCHEDTL`, `ADM.TITEM`, `ADM.TBDSCHE`, `ADM.TCMSCALL`

Loaded cached credentials.
### POST /vocmng/IbSttusMng/savePgmCallTm
- **Service:** `cj.bts.or.srm.domains.vocmng.service.IbSttusMngService.savePgmCallTm`
  - **Query:** `orderdtlJMapper.selectPgmBdRsltRead`
    - **Tables:** `ADM.TORDERITEM`, `ADM.TORDER`
  - **Query:** `cmscallMapper.savePgmCallTm`
    - **Tables:** `ADM.TCMSCALL`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveCustContactHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveCustContactHist`
  - **Query:** `contactMapper.selectCustContactHist`
    - **Tables:** `ADM.TCONTACT`, `ADM.TCUSTOMER`, `ADM.TPERINFO`

Loaded cached credentials.
### POST /vocmng/voc/retrieveVocInitService
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveVocInitService`
  - **Query:** `vendorJMapper.selectLtpCoVendorInf`
    - **Tables:** `ADM.TCODE`, `ADM.TVENDOR`
  - **Query:** `voccodeMapper.selectVocClsRead`
    - **Tables:** `ADM.TVOCCODE`

Loaded cached credentials.
### POST /vocmng/custContactHist/registerCustContactHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.registerCustContactHist`
  - **Query:** `contactMapper.selectGenerateContactNo`
    - **Tables:** `DUAL`, `ADM.Q_CONTACT_NO`, `ADM.Q_CONTACT_NO_MON`, `ADM.Q_CONTACT_NO_TUE`, `ADM.Q_CONTACT_NO_WED`, `ADM.Q_CONTACT_NO_THU`, `ADM.Q_CONTACT_NO_FRI`, `ADM.Q_CONTACT_NO_SAT`
  - **Query:** `contactMapper.insertCustContactHist`
    - **Tables:** `ADM.TCONTACT`


Loaded cached credentials.
### POST /vocmng/custContactHist/registerCustContactCommon
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.registerCustContactCommon`
  - **Query:** `contactMapper.selectContactNoFromUCID`
    - **Tables:** `ADM.TCONTACT`

Loaded cached credentials.
### POST /vocmng/custContactHist/registerCTIContactHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.registerCTIContactHist`
  - **Query:** `contactMapper.selectContactSysDate`
    - **Tables:** `DUAL`
  - **Query:** `contactMapper.updateStartCTIConnect`
    - **Tables:** `ADM.TCONTACT`
  - **Query:** `contactMapper.updateCloseCTIConnect`
    - **Tables:** `ADM.TCONTACT`
  - **Query:** `contactMapper.selectContactNoFromUCID`
    - **Tables:** `ADM.TCONTACT`
  - **Query:** `contactMapper.updateCTICustNo`
    - **Tables:** `ADM.TCONTACT`

Loaded cached credentials.
### POST /vocmng/custContactHist/modifyCustContactHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.modifyCustContactHist`
  - **Query:** `contactMapper.updateCustContactHist`
    - **Tables:** `ADM.TCONTACT`


Loaded cached credentials.
### POST /vocmng/custContactHist/retrieveCustContactHistInfo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveCustContactHistInfo`
  - **Query:** `contactMapper.selectCustContactHistInfo`
    - **Tables:** `ADM.TCONTACT`, `ADM.TCUSTOMER`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveCustbyContactHist
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveCustbyContactHistRead`
  - **Query:** `vendorJMapper.selectIsExtPartnerByCustNo`
    - **Tables:** `ADM.TCODE`
  - **Query:** `custContactHistJMapper.selectCustbyContactHistStr`
    - **Tables:** `ADM.TCONTACT`, `ADM.TVOC`, `ADM.TSR`, `ADM.TORDER`, `ADM.TPERINFO`
  - **Query:** `custContactHistJMapper.selectCustbyContactHist`
    - **Tables:** `ADM.TCONTACT`, `ADM.TVOC`, `ADM.TSR`, `ADM.TORDER`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveCustCallContact
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveCustCallContact`
  - **Query:** `vendorJMapper.selectIsExtPartnerByCustNo`
    - **Tables:** `ADM.TCODE`
  - **Query:** `custContactHistJMapper.selectCustCallContact`
    - **Tables:** `ADM.TCONTACT`, `ADM.TVOC`, `ADM.TSR`, `ADM.TORDER`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveContactNoFromUCID
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveContactNoFromUCID`
  - **Query:** `contactMapper.selectContactNoFromUCID`
    - **Tables:** `ADM.TCONTACT`

Loaded cached credentials.
### GET /vocmng/custContactHist/saveVoiceSystemInformation
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.saveVoiceSystemInformation`
  - **Query:** `custRecrdSumryMapper.selectRecText`
    - **Tables:** `ADM.TCUST_RECRD_SUMRY`
  - **Query:** `voiceSystemLogMapper.insertVoiceParamLog`
    - **Tables:** `ADM.TVOICE_SYSTEM_LOG`
  - **Query:** `custRecrdSumryMapper.insertVoiceSysInfo`
    - **Tables:** `ADM.TCUST_RECRD_SUMRY`

Loaded cached credentials.
### GET /vocmng/custContactHist/requestVoiceSystemInformation
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.requestVoiceSystemInformation`
  - (No direct database access found based on the provided analysis data.)

Loaded cached credentials.
### GET /vocmng/voc/retrieveLtpCoVendorInf
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocService.retrieveLtpCoVendorInf`
  - **Query:** `vendorJMapper.selectLtpCoVendorInf`
    - **Tables:** `ADM.TCODE`, `ADM.TVENDOR`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveContactHistPop
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveContactHistPop`
  - **Query:** `contactMapper.selectContactHistPop`
    - **Tables:** `ADM.TCONTACT`, `ADM.TCUSTOMER`, `ADM.TPERINFO`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveRecText
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveRecText`
  - **Query:** `custRecrdSumryMapper.selectRecText`
    - **Tables:** `ADM.TCUST_RECRD_SUMRY`

Loaded cached credentials.
### GET /vocmng/custContactHist/retrieveGenerateContactNo
- **Service:** `cj.bts.or.srm.domains.vocmng.service.CustContactHistService.retrieveGenerateContactNo`
  - **Query:** `contactMapper.selectGenerateContactNo`
    - **Tables:** `DUAL`, `ADM.Q_CONTACT_NO`, `ADM.Q_CONTACT_NO_MON`, `ADM.Q_CONTACT_NO_TUE`, `ADM.Q_CONTACT_NO_WED`, `ADM.Q_CONTACT_NO_THU`, `ADM.Q_CONTACT_NO_FRI`, `ADM.Q_CONTACT_NO_SAT`

Loaded cached credentials.
### GET /vocmng/vocclscd/retrieveVocCls
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.retrieveVocCls`
  - **Query:** `voccodeMapper.selectVocClsRead`
    - **Tables:** `ADM.TVOCCODE`

Loaded cached credentials.
### POST /vocmng/vocclscd/registerVocCls
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.saveVocCls`
  - **Query:** `voccodeMapper.selectVocClsDtl`
    - **Tables:** `ADM.TVOCCODE`
  - **Query:** `voccodeMapper.insertVocCls`
    - **Tables:** `ADM.TVOCCODE`
  - **Query:** `voccodeMapper.updateUseYnVocClsChild`
    - **Tables:** `ADM.TVOCCODE`
  - **Query:** `vocClsbyMngClauMapper.selectVocClsbyMngClauInfo`
    - **Tables:** `ADM.TVOCADMCODE`
  - **Query:** `vocClsbyMngClauMapper.deleteVocClsbyMngClau`
    - **Tables:** `ADM.TVOCADMCODE`
  - **Query:** `vocClsbyMngClauMapper.saveVocClsbyMngClau`
    - **Tables:** `ADM.TVOCADMCODE`

Loaded cached credentials.
### GET /vocmng/vocclscd/retrieveVocClsLst
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.retrieveVocClsLst`
  - **Query:** `voccodeMapper.selectVocClsLst`
    - **Tables:** `ADM.TVOCCODE`

Loaded cached credentials.
### GET /vocmng/vocclscd/retrieveVocClsDtl
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.retrieveVocClsDtl`
  - **Query:** `voccodeMapper.selectVocClsDtl`
    - **Tables:** `ADM.TVOCCODE`
  - **Query:** `vocClsbyMngClauJMapper.selectVocClsbyMngClau`
    - **Tables:** `ADM.TVOCADMCODE`, `ADM.TADMCODE`, `ADM.TPERINFO`
  - **Query:** `vocClsbyMngClauJMapper.selectComCdbyMngClau`
    - **Tables:** `ADM.TVOCADMCODE`, `ADM.TADMCODE`, `ADM.TCODE`

Loaded cached credentials.
### GET /vocmng/vocclscd/retrieveVocClsCdSrch
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.retrieveVocClsCdSrch`
  - **Query:** `voccodeMapper.selectVocClsCdSrch`
    - **Tables:** `ADM.TVOCCODE`

Loaded cached credentials.
### POST /vocmng/vocclscd/registerMngClauCd
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.registerMngClauCd`
  - **Query:** `mngClauCdMapper.selectMngClauCdInfo`
    - **Tables:** `ADM.TADMCODE`
  - **Query:** `mngClauCdMapper.insertMngClauCd`
    - **Tables:** `ADM.TADMCODE`

Loaded cached credentials.
### GET /vocmng/vocclscd/retrieveMngClauCdComboLst
- **Service:** `cj.bts.or.srm.domains.vocmng.service.VocClsCdService.retrieveMngClauCdComboLst`
  - **Query:** `mngClauCdMapper.selectMngClauCdComboLst`
    - **Tables:** `ADM.TADMCODE`

