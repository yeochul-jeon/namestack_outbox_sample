# or-ord-svc-db-tabel.md

| Controller | Endpoint | Summary | Database | Table |
|---|---|---|---|---|
| ExpectedGradeController | /ord/expectedGrade/inquireExpectedGrade | 예상등급조회 | ADM | TEXPECT_GRADE |
| GiftOrderManageController | /ord/giftOrderManage/registerGiftOrder | 기프트카드 주문 등록 | ORD | TORDERDTL, TORDER_GIFT_CARD, TORDER_GIFT_CARD_DTL |
| AllMyTourController | /ord/allMyTour/inquireAllMyTour | 올마이투어 조회 | 존재하지 않음 | 존재하지 않음 |
| CoreCmmnApiController | /ord/coreCmmnApi/inquireCommonCode | 공통코드 조회 | ADM | TCBCODE |
| ArsOrdController | /ord/arsOrd/inquireArsOrd | ARD 주문 조회 | ORD | TORDERDTL |
| KakaotalkIntlkController | /ord/kakaotalkIntlk/inquireKakaotalkMsg | 카카오톡 메시지 조회 | ADM | TCXRMNG_KAKAOTALK_MSG |
| KakaotalkIntlkController | /ord/kakaotalkIntlk/registerKakaotalkMsg | 카카오톡 메시지 등록 | ADM | TCXRMNG_KAKAOTALK_MSG |
| OrBatchMonitorController | /ord/orBatchMonitor/inquireBatchMonitor | 배치 모니터 조회 | ADM | TBATCH_MONITOR |
| OrBatchMonitorController | /ord/orBatchMonitor/inquireBatchMonitorDetail | 배치 모니터 상세 조회 | ADM | TBATCH_MONITOR_DTL |
| TempOrderController | /ord/tempOrder/inquireTempOrder | 임시주문 조회 | ORD | TTEMP_ORDER |
| TempOrderController | /ord/tempOrder/registerTempOrder | 임시주문 등록 | ORD | TTEMP_ORDER |
| TempOrderController | /ord/tempOrder/modifyTempOrder | 임시주문 수정 | ORD | TTEMP_ORDER |
| TempOrderController | /ord/tempOrder/removeTempOrder | 임시주문 삭제 | ORD | TTEMP_ORDER |
| PreOrderPayController | /ord/preOrderPay/inquirePreOrderPay | 선주문 결제 조회 | ORD | TPREORD_PAY |
| PreOrderController | /ord/preOrder/inquirePreOrder | 선주문 조회 | ORD | TPREORD |
| PreOrderController | /ord/preOrder/registerPreOrder | 선주문 등록 | ORD | TPREORD |
| OneDayUnlimitedCashController | /ord/oneDayUnlimitedCash/inquireOneDayUnlimitedCash | 원데이 무제한 캐시 조회 | ORD | TONE_DAY_UNLIMITED_CASH |
| ChangedCartChannelController | /ord/changedCartChannel/inquireChangedCartChannel | 변경된 장바구니 채널 조회 | ORD | TCHANGED_CART_CHANNEL |
| EtcCxrMngController | /ord/etcCxrMng/inquireEtcCxrMng | 기타 고객 경험 관리 조회 | ADM | TCXRMNG |
| EtcCxrMngController | /ord/etcCxrMng/registerEtcCxrMng | 기타 고객 경험 관리 등록 | ADM | TCXRMNG |
| EtcCxrMngController | /ord/etcCxrMng/modifyEtcCxrMng | 기타 고객 경험 관리 수정 | ADM | TCXRMNG |
| EtcCxrMngController | /ord/etcCxrMng/removeEtcCxrMng | 기타 고객 경험 관리 삭제 | ADM | TCXRMNG |
| VouchrCpnSnddeliHistReadController | /ord/vouchrCpnSnddeliHistRead/inquireVouchrCpnSnddeliHist | 바우처 쿠폰 발송 이력 조회 | ORD | TVOUCHR_CPN_SNDDELI_HIST |
| VouchrCpnOrdReadController | /ord/vouchrCpnOrdRead/inquireVouchrCpnOrd | 바우처 쿠폰 주문 조회 | ORD | TVOUCHR_CPN_ORD |
| VouchrCpnOrdDtlReadController | /ord/vouchrCpnOrdDtlRead/inquireVouchrCpnOrdDtl | 바우처 쿠폰 주문 상세 조회 | ORD | TVOUCHR_CPN_ORD_DTL |
| VouchrCpnItemOrdHistReadController | /ord/vouchrCpnItemOrdHistRead/inquireVouchrCpnItemOrdHist | 바우처 쿠폰 아이템 주문 이력 조회 | ORD | TVOUCHR_CPN_ITEM_ORD_HIST |
| UseselfOutwSttusController | /ord/useselfOutwSttus/inquireUseselfOutwSttus | 자가출고 현황 조회 | ADM | TUSESELF_OUTW_MSTR |
| UseselfOutwReqRegController | /ord/useselfOutwReqReg/registerUseselfOutwReq | 자가출고 요청 등록 | ADM | TUSESELF_OUTW_MSTR, TUSESELF_OUTW_DTL |
| UseselfOutwReqAppvSttusController | /ord/useselfOutwReqAppvSttus/inquireUseselfOutwReqAppvSttus | 자가출고 요청 승인 현황 조회 | ADM | TUSESELF_OUTW_MSTR |
| UseselfOutwOrdCrteController | /ord/useselfOutwOrdCrte/registerUseselfOutwOrd | 자가출고 주문 생성 | ADM | TSELFITEMDTL, TSELFITEM, TITEM, TRECEIVER, TPERINFO, TORDERDTL, TCOSTCENTER, TCHANNEL, TWBILLDTL, TWBILL, TSTRUCTURE, TORDERPROC, TCBCODE, TCUST_PAY_HIST, TCUST_CARD_PROV_AGR, TCUST_CARD_PROV_AGR_HIST, TVEN_COMP_DELIV_PREPLN_DD, TCOMMERCE_REF_INF, TORDER_CREATE_CHK, TORDER_CREATE, TORDER_PROGRESS_ETC, TCUSTOMER_INFORMATION_INSU, TITEMCHNL, TTM2S_CC_COUN_APLC, TPREORD_TRAN_TGT_ORD_ITEM, TPREORD_TRAN_CHN_OUTSL, TPREORD_TRAN_TGT_ORD, TPREORD_HIST, TORDER_PROGRESS_STATUS, TORDER_PROGRESS_DETAIL, TORDER_DETAIL_COST, TORDER_MESSAGE |
| UseselfNoReadController | /ord/useselfNoRead/inquireUseselfNo | 자가출고 번호 조회 | ADM | TSELFITEM, TSELFITEMDTL, TCOSTCENTER, TPERINFO, TITEM, TRECEIVER, TORDERPROC, TCODE |
| SlItembyChnReadController | /ord/slItembyChnRead/inquireSlItembyChn | 채널별 판매 아이템 조회 | ADM | TITEMCHNL |
| OrdTopBarController | /ord/ordTopBar/inquireOrdTopBar | 주문 상단바 조회 | ADM | TOBCALLDTL, TCBCODE, TVDI_USE_YN, TRECORD_EXT, TOBCALL_TELNOS, TSALES_CHANNEL, TVOC_TYPE_MANAGE, TCTI_CONTACT, TITEM, TVENDOR, TMD, TSR_DAMDANG, TVEN_SR_DAMDANG, TUCID_CONTACT_NO, TORDER_MASTER, TORDER_ITEM, TORDER_DETAIL, TORDER_PROGRESS, THOPEDELYVHIST, TCOUNSEL, TORDER_NUMBER, TCHANNEL_SALE, TORDER_PROGRESS_CHANGE, TRGLR_CHCK_WRK |
| LogiSubLnksController | /ordLegacy/logiSubLnks/changeOrdClaimDtlProgStepCd | 주문 불만 상세 진행 단계 코드 변경 | ORD | TORDERDTL, TORDERPROC, TORDERDTL_COST, TORDER_MESSAGE, TASM |
| LogiSubLnksController | /ordLegacy/logiSubLnks/registerPrtnrVenCompOutwHist | 파트너 협력 업체 출고 내역 등록 | ORD | TORDERDTL, TORDERPROC, TASM |
| LogiSubLnksController | /ordLegacy/logiSubLnks/registerVenCompOutwCustRcv | 협력 업체 출고 고객 인수 등록 | ORD | TORDERDTL, TORDERPROC, TORDERDTL_COST, TORDER_MESSAGE, TASM |
| LogiSubLnksController | /ordLegacy/logiSubLnks/registerOverseasDelivOutwCustRcv | 해외배송-출고/고객인수등록 | ORD | TORDERDTL, TORDERPROC, TORDERDTL_COST, TORDER_MESSAGE, TASM |
| OrdStockController | /ordRcpt/stock/registerPromCounQty | 단품접수-S프로모션 상담 수량 등록 | MALLOWN | TPROM_COUNSEL |
| OrdStockController | /ordRcpt/stock/changePromCounQtyDel | 주문재고-S프로모션 상담 수량 삭제 | MALLOWN | TPROM_COUNSEL |
| OrdStockController | /ordRcpt/stock/deleteOrdCounQty | 주문재고-S주문 상담 수량 삭제 | MALLOWN | TCOUNSEL, TPROM_COUNSEL, TORDERSTOCK, TORDERSTOCK_ORG, TITEM, TITEMVEN, TCHANNEL |
| OrdStockController | /ordRcpt/stock/deleteWaitOrdCounInf | 주문재고-대기주문상담정보삭제 | MALLOWN | TCOUNSEL |
| OrdStockController | /ordRcpt/stock/inquireOrderStockAdjustTarget | 주문재고-재고조정대상정보조회 | MALLOWN | TORDERSTOCK_ADJUST_TARGET, TORDERSTOCK, TITEM |
| OrdStockController | /ordRcpt/stock/registerOrderStockAdjust | 주문재고-재고조정등록 | MALLOWN | TORDERSTOCK_ADJUST_TARGET, TORDERSTOCK, TORDERSTOCK_ADJUST, TCBCODE |
| DeliveryExposureController | /delivery/exposure/find | 배송 노출관리 데이터 조회 | 존재하지 않음 | 존재하지 않음 |
| ComSapSettleController | /settle/ComSap/selectID064 | ID064 조회 | ADM | TPAMUSE, TINAMT, TEXTGC_USE_HIST |
| ComSapSettleController | /settle/ComSap/selectID071 | ID071 조회 | ADM | TPROMORESULT |
| ComSapSettleController | /settle/ComSap/selectID130 | ID130 조회 | ADM | TMOBILEAPPV, TCBCODE, TEXTHP_USE_HIST |
| ComSapSettleController | /settle/ComSap/selectID189 | ID189 조회 | ADM | TPAMUSE, TORDERPROC, TOTOURUSECANCEL, TGRPCOMP, TPERINFO, TOTOURINVOICE, TUSECOMPOTOURDSUM |
| ComSapSettleController | /settle/ComSap/selectID190 | ID190 조회 | ADM | TOTOURUSECANCEL, TINAMT, TORDCONFOTOURDSUM |
| ComSapSettleController | /settle/ComSap/selectID217 | ID217 조회 | ADM | TGROUPGIFTCERT, TOCCGCDSUM_TRADE |
| ComSapSettleController | /settle/ComSap/selectID218 | ID218 조회 | ADM | TPAMUSE, TINAMT, TUSEGCDSUM_TRADE |
| ComSapSettleController | /settle/ComSap/selectID219 | ID219 조회 | ADM | TPAMUSE, TINAMT, TUSEDEPOSITPAMDSUM |
| ComSapSettleController | /settle/ComSap/selectID220 | ID220 조회 | ADM | TCMSLINK, TINAMT, TCMS, TINAMTPROC, TCONFINAMTDTRADE |
| ComSapSettleController | /settle/ComSap/selectID221 | ID221 조회 | ADM | TSAVEAMTUSE, TINAMT, TSAVEAMTLINK, THSAVEAMTGIVE, TPAMREASONCODE, TCODE, TSTRUCTUREMON, TORDERDTL, TCSAVEAMTGIVE, TUSESAVEAMTDSUM_TRADE |
| ComSapSettleController | /settle/ComSap/selectID222 | ID222 조회 | ADM | TPAMUSE, TGRPCOMP, TPERINFO, TBIZPOINTUSE, TUSECOMPDSUM_BIZCAFE |
| ComSapSettleController | /settle/ComSap/selectID224 | ID224 조회 | ADM | THSAVEAMTGIVE, TORDERDTL, TPAMREASONCODE, TCODE, TSTRUCTUREMON, TCBCODE, TCSAVEAMTGIVE, TGIVESAVEAMTDSUM |
| ComSapSettleController | /settle/ComSap/selectID227 | ID227 조회 | ADM | TPAMGIVE, TINAMT, TGIVEDEPOSITPAMDSUM |
| ComSapSettleController | /settle/ComSap/selectID230 | ID230 조회 | ADM | TCMS, TVENDOR, TVIRTUALACNT, TB2BPRTNR, TINAMTDBANK |
| ComSapSettleController | /settle/ComSap/selectID231 | ID231 조회 | ADM | TCMSLINK, TINAMT, TCMS, TINAMTPROC, TCOMFINAMTDBANK |
| ComSapSettleController | /settle/ComSap/selectID232 | ID232 조회 | ADM | TPAMUSE, TINAMT, TBIZPOINTUSE, TCONFORDPOINTDSUM_BIZCAFE |
| ComSapSettleController | /settle/ComSap/selectID233 | ID233 조회 | ADM | TREPAY, TINAMT, TREPAYDOCC |
| ComSapSettleController | /settle/ComSap/selectID234 | ID234 조회 | ADM | TREPAY, TREPAYDPROC |
| ComSapSettleController | /settle/ComSap/selectID316 | ID316 조회 | ADM | TINAMT, TDEMAND, TCARDAPPVDSUM_FRAN |
| ComSapSettleController | /settle/ComSap/selectID322 | ID322 조회 | ADM | TDEMAND |
| ComSapSettleController | /settle/ComSap/selectID639 | ID639 조회 | ADM | TDEMAND |
| ComSapSettleController | /settle/ComSap/selectID977 | ID977 조회 | ADM | TEASYPAYHIST |
| ComSapSettleController | /settle/ComSap/selectID995 | ID995 조회 | ADM | TCARDIMDDCSUM |
| ComSapSettleController | /settle/ComSap/selectIF092 | IF092 조회 | ADM | TRECEIVEHISTOM |
| ComSapSettleController | /settle/ComSap/deleteID | ID별 데이터 삭제 | ADM | TEXTGC_USE_HIST, TPROMORESULT, TEXTHP_USE_HIST, TOTOURINVOICE, TUSECOMPOTOURDSUM, TORDCONFOTOURDSUM, TOCCGCDSUM_TRADE, TUSEGCDSUM_TRADE, TUSEDEPOSITPAMDSUM, TCONFINAMTDTRADE, TUSESAVEAMTDSUM_TRADE, TUSECOMPDSUM_BIZCAFE, TGIVESAVEAMTDSUM, TGIVEDEPOSITPAMDSUM, TINAMTDBANK, TCOMFINAMTDBANK, TCONFORDPOINTDSUM_BIZCAFE, TREPAYDOCC, TREPAYDPROC, TCARDAPPVDSUM_FRAN, TEASYPAYHIST, TCARDIMDDCSUM, TRECEIVEHISTOM |
| B2cSalesSettleController | /settle/b2c/retreiveOrdNoList | 주문번호로 조회 | ADM | B2C_SALES |
| B2cSalesSettleController | /settle/b2c/retreiveModifyList | 변경 대상 조회 | ADM | TSTOCKLDGRFITRHIST |
| B2cSalesSettleController | /settle/b2c/updateSalesDate | 매출일자 변경 | ADM | B2C_SALES |
| EtcCostController | /setlmng/etcCost/retrieveEtcCostList | 기타비용(일반) | ADM | TORDERCOSTFI |
| EtcCostController | /setlmng/etcCost/retrieveExtEtcCostList | 기타비용(대형제휴) | ADM | TORDERCOSTFI |
| EtcCostController | /setlmng/etcCost/retrieveCjGiftCardUseList | CJ기프트카드 회입내역 | ADM | TPAMUSEDSUM |
| EtcCostController | /setlmng/etcCost/retrievePamUseList | 상품권/예치금 잔액조회 | ADM | TPAMREM, TPAMGIVE, TPAMUSE |
| EtcCostController | /setlmng/etcCost/retrieveEtcAmountPayList | ID048 대형제휴 기준 금액 조회 | ADM | TORDERCOSTFI |
| CardPromoController | /setlmng/cardPromo/retrieveCardImdDcList | 즉시할인조회 | ADM | TCHANNEL, TSTRUCTUREMON, TSTRUCTURE, TCBCODE, TORDERDTLCO, TORDERITEM, TCARDIMDDCCUSTDTL, TORDERDTL, V_INAMT, V_DEMAND, TCARDDTL, TDEMANDDCMST, TDEMANDDCCARD, TITEM, TITEMADD, TITEMDTL_PROM_EXCEPT |
| CardPromoController | /setlmng/cardPromo/retrieveCardPromoList | 청구할인조회 | ADM | TCHANNEL, TSTRUCTUREMON, TSTRUCTURE, TCBCODE, TORDERDTLCO, V_DEMAND, TCARDDTL, TORDERDTL, TORDERITEM, TDEMANDDCMST, TDEMANDDCCARD, TITEM, TITEMADD, TITEMDTL_PROM_EXCEPT |
| RglrPayController | /RglrPayController/createRglrDelivScdul | 정기배송스케쥴생성 | ADM | TRECEIVER, TRGLR_PAY_DELIV, TRGLR_PAY, TRGLR_PAY_ITEM, TRGLR_PAY_TOKEN, TRGLR_PAY_INAMT, TRGLR_PAY_ORD_SCHD |
| RglrPayController | /RglrPayController/closeRglrDelivScdul | 정기배송스케쥴종료 | ADM | TRGLR_PAY, TRGLR_PAY_ORD_SCHD |
| RglrPayController | /RglrPayController/skipRglrDelivTme | 정기배송회차건너뛰기 | ADM | TRGLR_PAY, TRGLR_PAY_ORD_SCHD |
| RglrPayController | /RglrPayController/changeRglrDelivDelivplc | 정기배송배송지변경 | ADM | TRECEIVER, TRGLR_PAY_DELIV |
| RglrPayController | /RglrPayController/completeRglrDelivTme | 정기배송회차완료 | ADM | TRGLR_PAY, TRGLR_PAY_ORD_SCHD, TRGLR_PAY_TOKEN, TRGLR_PAY_INAMT |
| RglrPayController | /RglrPayController/cancelRglrDelivTme | 정기배송회차취소 | ADM | TRGLR_PAY, TRGLR_PAY_ORD_SCHD |
| RglrDelivOrdCrteController | /ordRcpt/ordBatch/rglrDelivOrdCrte | 정기결제주문생성 | ADM | TRGLR_PAY_ORD_SCHD, TRGLR_PAY_ITEM, TRGLR_PAY, TRGLR_PAY_DELIV, TCUSTADDR, TITEM, TITEMCHNL, TCUSTOMER, TSEGCUST, TGRADEDCBAS, TITEMPRICE, TDEMANDDCMST, TDEMANDDCCARD, TITEMADD, TITEMDTL_PROM_EXCEPT, TRECEIVER, TADDR_LNM, TADDR_ROAD_NM, TORDERDTLCO, TORDERITEM, V_INAMT, V_DEMAND, TCARDDTL, TCHANNEL, TSTRUCTUREMON, TSTRUCTURE, TCBCODE, TPAMREASONCODE, TCODE, TCSAVEAMTGIVE, TSAVEAMTLINK, THSAVEAMTGIVE, TSAVEAMTUSE, TPAMUSEDSUM, TPAMREM, TPAMGIVE, TINAMT, TCMS, TVENDOR, TVIRTUALACNT, TB2BPRTNR, TEASYPAYHIST, TCARDIMDDCSUM, TRECEIVEHISTOM, TDEMAND, TCARDAPPVDSUM_FRAN, TPROMORESULT, TEXTHP_USE_HIST, TMOBILEAPPV, TOTOURINVOICE, TUSECOMPOTOURDSUM, TORDCONFOTOURDSUM, TGROUPGIFTCERT, TOCCGCDSUM_TRADE, TUSEGCDSUM_TRADE, TUSEDEPOSITPAMDSUM, TCONFINAMTDTRADE, TUSESAVEAMTDSUM_TRADE, TUSECOMPDSUM_BIZCAFE, TGIVESAVEAMTDSUM, TGIVEDEPOSITPAMDSUM, TINAMTDBANK, TCOMFINAMTDBANK, TCONFORDPOINTDSUM_BIZCAFE, TREPAYDOCC, TREPAYDPROC, TRGLR_PAY_TOKEN, TRGLR_PAY_INAMT |
| RealtmStockIntlkMngController | /realtmStockIntlkMng/retrieveRealtmStockIntlkList | 협력사 실시간재고 연동관리 리스트 조회 | ORD | TVEN_STOCK_MAPNG_INFO, ADM.TVENDOR |
| RealtmStockIntlkMngController | /realtmStockIntlkMng/saveRealtmStockIntlkDtl | 협력사 실시간재고 연동관리 등록 및 수정 | ORD | TVEN_STOCK_MAPNG_INFO, ADM.TVENDOR |
| ServiceVendorMngController | /ptnmng/servicevendor/retrieveServiceVendorList | 파트너시스템 서비스대상업체조회 조회 | ADM | TSI_SERVICE_VENDOR, ADM.TVENDOR, ADM.TPAUSERS |
| ServiceVendorMngController | /ptnmng/servicevendor/saveServiceVendor | 파트너시스템 서비스대상업체 저장 | ADM | TSI_SERVICE_VENDOR |
| ServiceListMngController | /ptnmng/serviceList/retrieveServiceList | 파트너시스템 서비스목록 조회 | ADM | TSI_SERVICE_LIST, ADM.TCODE, ADM.TPAUSERS |
| ServiceListMngController | /ptnmng/serviceList/retrieveApiServiceCdList | 파트너시스템 서비스코드 조회 | ADM | TSI_SERVICE_LIST, ADM.TCODE |
| ServiceListMngController | /ptnmng/serviceList/saveServiceList | 파트너시스템 서비스목록 저장 | ADM | TSI_SERVICE_LIST |
| ServiceByVendorMngController | /ptnmng/serviceByVendor/retrieveServiceByVendorList | 파트너시스템 업체별서비스관리 조회 | ADM | TSI_SERVICE, TSI_SERVICE_VENDOR, TSI_SERVICE_LIST, TVENDOR, TPAUSERS |
| ServiceByVendorMngController | /ptnmng/serviceByVendor/inquireServiceList | 파트너시스템 업체별서비스관리 > 서비스항목 팝업조회 | ADM | TSI_SERVICE_LIST, TCODE, TPAUSERS |
| ServiceByVendorMngController | /ptnmng/serviceByVendor/inquireVendorList | 파트너시스템 업체별서비스관리 > 일괄적용 팝업 협력사 조회 | ADM | TSI_SERVICE_VENDOR, TVENDOR |
| ServiceByVendorMngController | /ptnmng/serviceByVendor/saveServiceByVendor | 파트너시스템 업체별서비스관리 저장 | ADM | TSI_SERVICE |
| ServiceByVendorMngController | /ptnmng/serviceByVendor/saveServiceByVendorBulk | 파트너시스템 업체별서비스관리 저장 | ADM | TSI_SERVICE |
| QnaMngController | /ptnmng/qnaMng/retrieveQnaMngList | Q&A관리리스트 조회 | ADM | TPAWR, TVENDOR, TPAWRDIV |
| QnaMngController | /ptnmng/qnaMng/retrieveDivCodeList | 업무코드 조회 | ADM | TPAWRDIV |
| QnaMngController | /ptnmng/qnaMng/retrieveQnaMngDetail | Q&A관리상세 조회 | ADM | TPAWR, TVENDOR, TPAWRCHARGE, TPAUSERS |
| QnaMngController | /ptnmng/qnaMng/saveQnaMng | Q&A 저장 | ADM | TPAWR |
| PtnNoticeController | /ptnmng/PtnNotice/selectNoticeList | 공지사항 목록조회 | ADM | TNOTICE, TPTN_NOTICE |
| PtnNoticeController | /ptnmng/PtnNotice/selectNoticeDetail | 공지사항 상세조회 | ADM | TNOTICE, TPTN_NOTICE |
| PtnNoticeController | /ptnmng/PtnNotice/insertNotice | 공지사항 추가 | ADM | TNOTICE, TPTN_NOTICE |
| PtnNoticeController | /ptnmng/PtnNotice/updateNotice | 공지사항 수정 | ADM | TNOTICE, TPTN_NOTICE |
| PtnNoticeController | /ptnmng/PtnNotice/deleteNotice | 공지사항 삭제 | ADM | TNOTICE, TPTN_NOTICE, TPTN_NOTICE_VENDOR, TPTN_NOTICE_MENU |
| PtnNoticeController | /ptnmng/PtnNotice/checkPswdValid | 공지사항 비밀번호 유효성 체크 | ADM | TNOTICE, TPTN_NOTICE |
| PtnNoticeController | /ptnmng/PtnNotice/selectVendorList | 공지사항 협력업체 목록 | ADM | TVENDOR, TPTN_NOTICE_VENDOR |
| PtnNoticeController | /ptnmng/PtnNotice/checkVendorValid | 공지사항 협력업체 유효성 조회 | ADM | TVENDOR, TPTN_NOTICE_VENDOR |
| PtnNoticeController | /ptnmng/PtnNotice/insertVendorList | 협력사 입력 | ADM | TPTN_NOTICE_VENDOR |
| PtnNoticeController | /ptnmng/PtnNotice/selectPopupList | 공지사항 팝업메뉴 목록 | ADM | TPOPUP, TPTN_NOTICE_MENU |
| PtnNoticeController | /ptnmng/PtnNotice/selectMenuListNew | 공지사항 팝업메뉴 2.0 전체목록 | ADM | TPOPUP, TPTN_NOTICE_MENU |
| PtnNoticeController | /ptnmng/PtnNotice/selectMenuListOld | 공지사항 팝업메뉴 1.0 전체목록 | ADM | TPOPUP, TPTN_NOTICE_MENU |
| PtnNoticeController | /ptnmng/PtnNotice/insertPopupList | 팝업메뉴 입력 | ADM | TPTN_NOTICE_MENU |
| PtnNoticeController | /ptnmng/PtnNotice/selectFileList | 공지사항 첨부파일 목록 | ADM | TFILE, TPTN_NOTICE_FILE |
| PtnFaqMngController | /ptnmng/faq/retrieveFaq | FAQ 내역 조회 | ADM | TPAFAQ, TCODE, TPERINFO |
| PtnFaqMngController | /ptnmng/faq/saveFaq | FAQ 내역 저장 | ADM | TPAFAQ |
| PtnEntrPropslItemController | /ptnmng/entrPropslItem/createPtnNewUser | 신규 입점 사용자 등록 | ADM | TPAUSERS, TCODE, TVENADDINFO, CMMN.GROUP_ID_SEQ, CMMN.USER_SEQUENCE_ID, PRTNR.GROUP_SECURITY, PRTNR.USER_ROLE_MAPNG_INF, PRTNR.ROLE_INF |
| FirewallReqMngController | /ptnmng/firewallReqMng/selectFirewallReqMngList | 방화벽요청관리리스트 조회 | ADM | TSI_SERVICE_VENDOR, TVENDOR, TPAUSERS, TSI_SERVICE_VENDOR_LOG |
| FirewallReqMngController | /ptnmng/firewallReqMng/saveFirewallReqMng | 방화벽요청정보 저장 | ADM | TSI_SERVICE_VENDOR |
| ApiDocsMngController | /ptnmng/apiMng/selectApiDocsSpec | API 서비스 리스트 조회 | ADM | TSI_SERVICE_LIST, TCODE, TAPI_SPEC, TPAUSERS |
| ApiDocsMngController | /ptnmng/apiMng/selectApiServiceInfo | API 서비스 코드조회 | ADM | TSI_SERVICE_LIST, TCODE |
| ApiDocsMngController | /ptnmng/apiMng/saveApiDocsSpec | API Spec 저장 | ADM | TAPI_SPEC, TSI_SERVICE_LIST |
| PresentOutBoundController | /present/createOutbound | OB대상자 배치 등록 - 선물배송 고르기 OB생성용 | ADM | TCODE, TOBCUST, TOB, TSI_SERVICE_VENDOR_LOG, TSI_SERVICE_VENDOR, TCOMPANY, TDEPT, TWORKPART, TSCWORKPART, TPERINFO, TSCWORKPARTHIST, TSURVEYSEGOFFER, TOFFER, TSURVEYSEG, TSURVEY, TSURVEYITEM, TSURVEYQUESTANS, TSURVEYKEYWORD, TSURVEYRELKEYWORD, TCUSTOMER, TCUSTADDR, TRECEIVER, TORDERITEM, TKAKAOMSG, TKAKAORECEIVER, TORDER_CPN_PUBLIC, TORDER_CPN_PUBLIC_TVADD, TORDERCLAIMINFO, TITEM, ORD.QOB_NO_SEQ |
| PresentController | /present/recent-received-count | 최근받은선물개수회 조회 | ADM | TORDERPRESENT |
| OrdCostController | /paycmmn/ordCost/registerOrdCost | 주문비용등록 | ORD | TORDERCOST, TORDERCOSTADD |
| CustPayInfController | /paycmmn/payCustInf/registerCustPayInf | 고객결제정보등록 | ADM | TCUSTPAM, TCUSTCAGREE, TCUSTCAGREEHIST |
| OutDeliverPlanSmsManageController | /ordMod/outDeliverPlanSmsManage/selectDelvplnSmsDeleteList | 출고 배송일 SMS관리 테이블 조회 | ADM | TDELVPLNSMS, TITEM, TVENDOR, TCODE |
| OutDeliverPlanSmsManageController | /ordMod/outDeliverPlanSmsManage/storeDelvplnSmsDeleteList | 출고 배송일 SMS관리 테이블 저장 | ADM | TDELVPLNSMS |
| OrderProgMngController | /ordMod/orderProgMng/retrieveOrderProgChg | 주문진행단계 변경 대상건 조회 | ADM | TORDERDTL, TITEM, TORDERPRESENT, TCODE |
| OrderProgMngController | /ordMod/orderProgMng/saveOrderProgChg | 주문진행단계대상 변경 | ADM | TORDERPROC, TORDERDTL, TORDERPROCCHGHIST |
| OrdModController | /ordMod/ordMod/savePayMethPriorRank | 결제수단우선순위저장 | ADM | TCODE |
| OrdModController | /ordMod/ordMod/retrievePayMethPriorRank | 결제수단우선순위조회 | ADM | TCODE |
| OrdModController | /ordMod/ordMod/retrieveCustDelivplc | 고객배송지조회 | ADM | TRECEIVER |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistCjOpntHist | 고객주문이력CJ원포인트이력조회 | ADM | THPOINTSAVEAMT_OM, TORDERDTL, TITEM, TCODE, TPAMREASONCODE, TSAVEAMTREM, TCSAVEAMTGIVE, TSRPROC, TSCWORKPART, TPERINFO, TWORKPART, TDEPT, TSR, TVOCCODE, TCUSTOMER, TSEGCUST, TORDERITEM, TWBILLDTL, TWBILL, TORDERETC, TPROGRAM, TCHANNELSALE, ORD.V_TPLN_EXHB, TITEMADD, TDELIVCST, TORDERRETURN_RSN, TCVSRECALLAPPVHIST, TORDERRORD, TORDERPRESENT, MALLOWN.TMCOM_PARTNER_CODE, MALLOWN.TPARTNERM |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistSrProcHistDtl | 고객주문이력SR처리이력상세조회 | ADM | TSRPROC, TSCWORKPART, TPERINFO, TWORKPART, TDEPT |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistSrProcHist | 고객주문이력SR처리이력조회 | ADM | TSR, TSEGCUST, TCUSTOMER, TITEM, TPERINFO, TASSIGNROLE, TITEMCHNL, TVENPLACE, TVOCCODE, TORDERDTL |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistSaveamtDtl | 고객주문이력적립금상세조회 | ADM | TSAVEAMTUSE, TPAMREASONCODE, THSAVEAMTGIVE, TCODE, TPERINFO, TWORKPART, TCJSHOP_CARD, TCJBRAND_CARD, TORDERDTL, TITEM |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistSaveamtHist | 고객주문이력적립금이력조회 | ADM | THSAVEAMTGIVE, TORDERDTL, TITEM, TPAMREASONCODE, TCSAVEAMTGIVE, TSAVEAMTUSE, TRESTRICTORDER |
| OrdModController | /ordMod/ordMod/retrieveCustOrdHistOrdProg | 고객주문이력주문진행조회 | ADM | TORDERDTL, TITEM, TORDERPROC, TPERINFO, TINAMT, TBANK, TINAMTBANK, TINAMT_PAR_CNCL, TORDERCOST, TVOCCODE, TCHANNEL, TADDR_LNM, TORDERITEM, TPROGRAM, TCODE, TCHANNELSALE, ORD.V_TPLN_EXHB, TORDAPPTDLVINFO, TRECEIVER_CVS, TCVSRECALLAPPVHIST, TWBILLDTL, TWBILL, TORDERETC, TORDERSENDER, TITEMADD, TORDERDC, TSECONDDELIVPLAN, THOPEDELY, TORDERCANCELTARGET, TSTOCKPENALTY, THPOINTSAVEAMT_OM, TITEMCHNL, TITV_ITEM, TITV_PGM, TITV_PGM_GROUP, TPRESHIPREQ, TCUSTADDR, TORDERPRESENT, TRESTRICTORDER, TPAYCOINAMT, TPAYCOAPPV, TPAYNOWPGAPPV, TKAKAOPAYAPPV, TSYRUPAPPV, TTVPAYAPPV, TDEMAND, TORDER_CPN_PRTNR, TREPAYINFO |
| OrdModController | /ordMod/ordMod/retrieveCustDcCpn | 고객할인쿠폰조회 | ADM | TCUSTDCCOUPON, TORDERITEM, TITEM, TPERINFO, TOFFER, TCHANNEL |
| OrdModController | /ordMod/ordMod/retrieveOfferInf | 오퍼정보조회 | ADM | TOFFER |
| OrdModController | /ordMod/ordMod/retrieveWaitOrdList | 대기주문리스트조회 | ADM | TCOUNORDER, TITEM, TCUSTOMER, TCUSTADDR, TRECEIVER, TPERINFO, TORDERITEM, TITEMCHNL, TVENDOR, TCHANNEL, TOBCUST |
| OrdModController | /ordMod/ordMod/retrieveWaitOrdHist | 대기주문내역조회 | ADM | TCOUNORDER, TITEM, TORDERITEM, TOBCUST, TCUSTOMER, TPERINFO, TITEMADD |
| OrdModController | /ordMod/ordMod/changeWaitOrdHist | 대기주문내역변경 | ADM | TCOUNORDER |
| OrdModController | /ordMod/ordMod/retrieveOrdLimit | 주문제한조회 | ADM | TORDERITEM, TRESTRICTORDER, TITEM, TPERINFO |
| OrdModController | /ordMod/ordMod/saveOrderLimitDetail | 주문제한등록 | ADM | TRESTRICTORDER |
| OrdModController | /ordMod/ordMod/retrieveCustInfo | 주문제한등록화면 > 고객정보 조회 | ADM | TCUSTADDR, TSEGCUST |
| OrdModController | /ordMod/ordMod/retrieveOrderLimitTarget | 주문제한등록화면 > 주문번호 엑셀 업로드 | ADM | TORDERITEM, TITEM, TRESTRICTORDER |
| OrdModController | /ordMod/ordMod/deleteOrderLimitCustomer | 주문제한 취소(삭제) | ADM | TORDERREJECTCUST |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveBdItemInf | 방송상품정보조회 | ADM | TBDSCHEDTL, TITEM, TITEMCHNL, TCHANNEL, TSBSCHEDTL |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveOshPlusBdItem | 오쇼핑플러스방송상품조회 | ADM | TBDSCHE, TBDSCHEDTL, TITEM, TITEMCHNL, TCHANNEL, TCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveGenItemInf | 일반상품정보조회 | ADM | TITEM, TITEMCHNL, TCHANNEL, TITEMADD, TCBCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrievePreshipOrd | 기출하주문조회 | ADM | TORDERITEM, TITEM, TRECEIVER, TCODE, TORDERDTL, TCARDIMDDCCUSTDTL, TCARDIMDDCCUSTDDMST, TCARDIMDDCMST, TORDERITEMPROMO, TPROM, TORDERCOUPONDC, TCJCARDDC_MEMBER, TCJMEMBER, TGRPCOMP, TCJCOMP_CARD, TGRADEDCUSE, TORDERETC, TORDERCOST, TORDERCOSTADD |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCbcode | cbcode정보조회 | ADM | TCBCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveOrdSnddeliusrInf | 주문발송자정보조회 | ADM | TCUSTOMER, TCUSTADDR, TORDERSENDER |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveLtpCoInamtAcnt | 대형제휴입금계좌조회 | ADM | TEXT_VIRTUALACNT, TINAMTBANK |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCardHdayInf | 카드휴일정보조회 | ADM | DUAL |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveRecentPay | 최근결제조회 | ADM | TINAMT, TCUSTPAM, TBANK |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveVirtAcnt | 가상계좌조회 | ADM | TVIRTUALACNT, TCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveItemTypePayUablTp | 상품타입별결제불가수단조회 | ADM | TITEM_PAY_CTRL_ATTR, TCBCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveTm2sCcItemChn | 2차컨택상품채널조회 | ADM | TITEMCHNL |
| OrdRcptController | /ordRcpt/ordRcpt/saveTm2sCcItemCounInf | 2차컨택상품상담정보저장 | ADM | TCOUNORDERSTATUS |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCustBasInf | 고객기본정보조회 | ADM | TCJMEMBER, TCUSTOMER, TCUSTADDR, TSEGCUST, TSEGCODE, TPERINFO, TRELUST |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCpnIssueStreInf | 쿠폰발행저장정보조회 | ADM | TITEM_CPN_INFO, TITEMCHNL, TITEM_CPN_INFO_ADD, TITEM |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCartItemYn | 장바구니상품여부조회 | ADM | TORDERDTL, TITEMADD |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCustOrdRejectUabl | 고객주문거절불가조회 | ADM | TCUSTOMER, TSEGCUST, TSEGCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveRecordInf | 녹취정보조회 | ADM | TITEM, TCUSTOMER, TCUSTMAGREE, TCUSTADDR |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveOrdProg | 주문 진행 조회 | ADM | TORDERDTL, TITEM, TCHANNEL |
| OrdRcptController | /ordRcpt/ordRcpt/createOrdNo | 주문 번호 생성 | ORD | QORD_NO_SUN, QORD_NO_MON, QORD_NO_TUE, QORD_NO_WED, QORD_NO_THU, QORD_NO_FRI, QORD_NO_SAT |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveChnUnitCd | 채널단품코드조회 | ADM | TITEMCHNL, TCHANNEL, TITEM, TCODE |
| OrdRcptController | /ordRcpt/ordRcpt/modifyOrdProgStat | 주문 진행 상태 수정 | ADM | TORDERDTL, TORDERPROC, TORDERCOST |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveSlItemInf | 판매상품조회 | ADM | TITEM, TITEMCHNL, TITEMADD, TPROMDTL, TPROMOFFER, TPROM, TOFFERITEM |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveUnitInf | 단품정보조회 | ADM | TITEM, TITEMCHNL, TCHANNEL, TITEMVEN, TITEM_PAY_CTRL_ATTR |
| OrdRcptController | /ordRcpt/ordRcpt/createInamtNo | 입금 번호 생성 | ORD | QINAMT_NO_SUN, QINAMT_NO_MON, QINAMT_NO_TUE, QINAMT_NO_WED, QINAMT_NO_THU, QINAMT_NO_FRI, QINAMT_NO_SAT |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveBrthdtProvAgrYn | 생년월일제공동의정보조회 | ADM | TCUSTPAM, TCUSTCAGREE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveLtpCoEntrVenComp | 대형제휴입점협력업체조회 | ADM | TCODE, TVENDOR |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCusclearNo | 통관번호조회 | MALLOWN | TORDER_VEN_KEY |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveVouchrCpnItemYnChk | 이용권쿠폰상품여부체크조회 | ADM | TITEM, TITEM_CPN_INFO, TITEMVEN |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveBookPblprfrItemOrd | 도서공연상품주문조회 | ADM | TITEMADD, TITEM |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveVouchrItemRcvusrPurPossQty | 이용권상품인수자구매가능수량조회 | ADM | TITEM_CPN_INFO, TORDER_CPN_PUBLIC |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveExchRetngCndtn | 교환반품조건조회 | ADM | TITEM |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveItemAtenAgdDataYn | 상품주의사항데이터여부조회 | ADM | TDELIVCST, TITEM, TITEMADD, TITEMPOINT |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveOclkCpnSysRcvusrInf | 오클락쿠폰시스템인수자정보조회 | ADM | TCUSTADDR, TCUSTOMER |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveTourusrItemGbPackItemGb | 여행자상품구분팩상품구분조회 | ADM | TITEM_CPN_INFO |
| OrdRcptController | /ordRcpt/ordRcpt/createPreshipOrdNo | 기출하주문번호생성 | ORD | QORD_NO_SUN, QORD_NO_MON, QORD_NO_TUE, QORD_NO_WED, QORD_NO_THU, QORD_NO_FRI, QORD_NO_SAT |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCustAgrMent | 고객인수동의멘트조회 | ADM | TITEM, TITEMCHNL |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveTm2sCcInfRcptSurveyNo | 2차컨택정보접수서베이번호조회 | ADM | TSURVEY, TCUSTOMER, TSURVEYITEM, TSURVEYCUSTANS |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveLuprcDelivcostMinOrdAmt | 저단가배송비최소주문금액조회 | ADM | TITEMADD, TDELIVCST, TITEMCHNL |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveApptDdDelivYn | 지정일배송여부조회 | ADM | TITEMADD |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveTomorrowArvl | 내일도착조회 | ADM | TITEMADD |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveMountIslandArea | 산간도서지역조회 | ADM | TZIPPLACE, TCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveOnlyOneCustYn | 온리원고객여부조회 | ADM | TCBCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveArsTempOrd | ARS임시주문조회 | ADM | TARSORDER, TORDER, TARSORDERITEM, TITEM, TCHANNEL, TARSORDERINAMT, TARSORDERITEMPROM, TARSORDERDTL, TARSORDERDTLCOST |
| OrdRcptController | /ordRcpt/ordRcpt/createOrdInamtNo | 주문입금번호생성 | ORD | QINAMT_NO_SUN, QINAMT_NO_MON, QINAMT_NO_TUE, QINAMT_NO_WED, QINAMT_NO_THU, QINAMT_NO_FRI, QINAMT_NO_SAT |
| OrdRcptController | /ordRcpt/ordRcpt/retrievePtcrIncln1ClassCust | 특이성향1급고객조회 | ADM | TCBCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCashRciptIssueYn | 현금영수증발행여부조회 | ADM | TORDERDTAX, TORDERDTL, TITEM, TCBCODE, TINAMT, TOTOURUSECANCEL |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveInstlDelivPreplnDdItemInamtPreplnDd | 설치배송예정일상품입금예정일조회 | ADM | TCODE |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveFreeDelivcostCpn | 무료배송비쿠폰조회 | ADM | TORDERDC, TORDERDTL, TORDERITEM |
| OrdRcptController | /ordRcpt/ordRcpt/createWaitOrdNo | 대기주문번호생성 | ORD | QWAIT_ORD_NO_SUN, QWAIT_ORD_NO_MON, QWAIT_ORD_NO_TUE, QWAIT_ORD_NO_WED, QWAIT_ORD_NO_THU, QWAIT_ORD_NO_FRI, QWAIT_ORD_NO_SAT |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveCustCardInf | 고객카드정보조회 | ADM | TCUSTPAM, TINAMT, TCUSTCAGREE, TCARDDTL |
| OrdRcptController | /ordRcpt/ordRcpt/storeCjomCouponUseYn | CJOM 통합멤버십 쿠폰사용 등록/취소 전문 송수신 이력 저장 | ADM | TCJOM_IF_COUP_USE_HIST |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveDelivTmClsCdForDlv365 | 오네배송 API 호출 / 배송예정일 재산정 호출 | ADM | TITEMADD, TBDSCHEDTL, TITEM, TZIPPLACE, TCODE, TRECEIVER, TCENTZIPTODAYDELIV |
| OrdRcptController | /ordRcpt/ordRcpt/retrieveUnitInfo | 단품옵션정보 조회 (상품 api 호출) | 존재하지 않음 | 존재하지 않음 |
| OrdHistSmsTransController | /ordRcpt/ordBatch/ordHistSmsTrans | 주문내역SMS전송 | ADM | TCBCODE, TORDERDTL, TSTRUCTUREMON, TITEMCHNL, TINAMT, TCUSTMAGREE, TITEM, TRECEIVER, TITEM_PAY_CTRL_ATTR, TORDERETC, TITEMADD, TORDERPRESENT, TRGLR_PAY_ORD_SCHD, TRGLR_PAY, TKAKAOTALK_TEMPLATE, TVENPLACE, TMMSMSG, TMMSRECEIVER, TSMSMSG, TSMSRECEIVER, TMAILMSG, TMAILRECEIVER, TMAILATTACH, TMILE_APPV, TPAMUSE, TMILE_GIVE, TORDERDELAY, TORDERDELAYHIST, TORDERDELAY_SMS_SEND_HIST, TINAMT_SMS_SEND_HIST, TCOUNORDER, TOBCUST, TORDERITEM, TCUSTOMER, TCUSTADDR, TBANK, TINAMTBANK, TORDERDTAX, TORDERDC, TORDER_CPN_PUBLIC |