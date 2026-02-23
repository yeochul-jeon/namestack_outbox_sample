# or-spp-svc

| 대상 엔드포인트 | 주석 내용 요약 | 컨트롤러 명 | 데이터베이스 명 | 테이블 |
|---|---|---|---|---|
| /suppln/delivItemMng365/retrieve365DelivItemMngList | 365배송상품관리리스트조회 | DelivItemMng365Controller | ADM | TITEM, TITEMADD, TITEMVEN, TITEMCHNL, TITEM_DELIV_ATTR, TSTOCK, TITEMORG_BOX, TCODE, TSTRUCTURE, TMD, TLTPLANQTY_DAILY, TRES_ORDER_PLAN, TSALEPLAN, TLTPLANQTY, TBUYMD, TITEMKINDS, TCHANNEL, TWAREHOUSE, TVEN |
| /suppln/delivItemMng365/retrieve365DelivItemMngExcelUplosdList | 365배송상품관리 엑셀업로드조회 | DelivItemMng365Controller | ADM | TITEM, TITEMADD, TITEMVEN, TITEMCHNL, TCODE, TSTOCK, TITEMORG_BOX, TSTRUCTURE, TMD, TLTPLANQTY_DAILY, TRES_ORDER_PLAN, TSALEPLAN, TLTPLANQTY, TBUYMD, TITEMKINDS, TCHANNEL, TWAREHOUSE, TVENDOR |
| /suppln/delivItemMng365/save365DelivItemMng | 365배송상품관리저장 | DelivItemMng365Controller | ADM | TITEM, TITEMADD, TITEMVEN, TITEMCHNL, TCODE, TLTPLANQTY_DAILY, TRES_ORDER_PLAN, TSALEPLAN, TLTPLANQTY, TOUTLEADTM, TITEMORG_BOX |
| /suppln/delivPreplnDdAddDaysApi/retrieveDelivPreplnDdAddDays | 익일배송불가지역_리드타임_API | DelivPreplnDdAddDaysApiController | ADM | TZIPPLACE, TCODE |
| /supPln/deliv365/retrieveDelivTmClsCdForDlv365 | 오네배송 API | Dlv365orderController | 존재하지 않음 | 존재하지 않음 |
| /suppln/intgSupPlnMng/retrieveChnItemList | 채널상품 조회 | IntgSupPlnMngController | ADM | TVENDOR, TITEMVEN, TITEM, TITEMCHNL, TMDLINK, TCHANNEL, TCODE, TOUTLEADTM, TPACKUNIT |
| /suppln/intgSupPlnMng/retrieveBdSchePgmLst | 방송편성일정 조회 | IntgSupPlnMngController | ADM | TBDSCHE, TBDSCHEDTL, TPROGRAM, TITEMCHNL, TPACKUNIT, TITEM |
| /suppln/intgSupPlnMng/retrieveDailySupPlnExstYn | Daily공급계획 존재여부체크 조회 | IntgSupPlnMngController | ADM | TITEM, TSALEPLAN, TPACKUNIT |
| /suppln/intgSupPlnMng/retrieveDailySupPlnItemLst | 상품조회(DAILY공급계획) | IntgSupPlnMngController | ADM | TITEM, TCODE, TITEMCHNL, TITEMVEN, TPACKUNIT |
| /suppln/intgSupPlnMng/retrieveDailySupPlnOutwPossDd | 물류출고가능일 조회 | IntgSupPlnMngController | ADM | TLOGIHDAY |
| /suppln/intgSupPlnMng/retrieveIntgSupPln | 통합공급계획 조회 | IntgSupPlnMngController | ADM | TRES_ORDER_PLAN, TCODE, TITEMCHNL, TMDLINK, TSALEPLAN, TSALEPLANDTL, TLTPLANQTY_DAILY, TLTPLANQTY, TITEM, TVENDOR, TISSUE_AVL_TIME, TOUTLEADTM, TLT_SPL_PLAN_AUTH_DAILY, TITEMADD, TITEMVEN |
| /suppln/intgSupPlnMng/retrieveIntgSupPlnAll | 통합공급계획 전체상세조회 | IntgSupPlnMngController | ADM | DUAL, TSALEPLAN, TITEM, TCODE, TBDSCHE, TBDSCHEDTL, TITEMCHNL, TMDLINK, TLTPLANQTY_DAILY, TLTPLANQTY, TRES_ORDER_PLAN, TVENDOR, TISSUE_AVL_TIME, TOUTLEADTM, TLT_SPL_PLAN_AUTH_DAILY, TITEMADD, TLOGIHDAY, TPROGRAM, TSALEPLANDTL, TCHANNEL |
| /suppln/intgSupPlnMng/retrieveLtSupPlnLst | LT공급계획 조회 | IntgSupPlnMngController | ADM | TITEM, TLTPLANQTY, TVENDOR, TITEMCHNL, TCODE, TOUTLEADTM |
| /suppln/intgSupPlnMng/retrieveDailySupPlnLst | Daily공급계획 조회 | IntgSupPlnMngController | ADM | TSALEPLAN, TITEM, TPROGRAM, TSALEPLANDTL, TCODE, TBDSCHE, TBDSCHEDTL, TLOGIHDAY |
| /suppln/intgSupPlnMng/retrieveResrvSupPlnLst | 예약공급계획 조회 | IntgSupPlnMngController | ADM | TRES_ORDER_PLAN, TITEM, TVENDOR, TITEMCHNL, TCODE |
| /suppln/intgSupPlnMng/retrieveLtDailySupPlnLst | LT Daily 공급계획상세 조회 | IntgSupPlnMngController | ADM | TLTPLANQTY_DAILY, TITEM, TITEMCHNL, TCODE, TLOGIHDAY |
| /suppln/intgSupPlnMng/retrieveSupPlnStreUablItemLst | 공급계획저장불가 상품목록 조회 | IntgSupPlnMngController | ADM | TITEM, TITEMADD, TITEMVEN, TITEMCHNL, TCODE, TOUTLEADTM, TLTPLANQTY_DAILY, TRES_ORDER_PLAN, TSALEPLAN, TLTPLANQTY |
| /suppln/intgSupPlnMng/retrieveLtdSupPlnOutwPossDdLst | LT Daily공급계획 출고가능일 조회 | IntgSupPlnMngController | ADM | TLTPLANQTY_DAILY, TLOGIHDAY |
| /suppln/intgSupPlnMng/saveLtSupPln | LT공급계획등록 | IntgSupPlnMngController | ADM | TITEMCHNL, TLTPLANQTY, TITEM, TOUTLEADTM, TCODE, TITEMADD, TITEMVEN |
| /suppln/intgSupPlnMng/saveDailySupPln | Daily공급계획 등록 | IntgSupPlnMngController | ADM | TITEMCHNL, TSALEPLAN, TITEM, TLOGIHDAY, TSALEPLANDTL, TEMAIL_TMPL, TMDLINK, TCODE, TVENDOR, TCHANNEL, TPROGRAM |
| /suppln/intgSupPlnMng/saveDailySupPlnProgStep | Daily공급계획 진행단계 변경 | IntgSupPlnMngController | ADM | TSALEPLAN, TSALEPLANDTL |
| /suppln/intgSupPlnMng/saveResrvSupPln | 예약공급계획 등록 | IntgSupPlnMngController | ADM | TRES_ORDER_PLAN |
| /suppln/intgSupPlnMng/saveLtDailySupPln | L/T Daily공급계획 등록 | IntgSupPlnMngController | ADM | TLTPLANQTY_DAILY, TLTPLANQTY_DAILY_DTL |
| /suppln/intgSupPlnMng/saveChangeLeadTime | 리드타임 변경 | IntgSupPlnMngController | ADM | TITEMVEN |
| /suppln/intgSupPlnMng/saveResrvSupPlnFin | 예약공급계획 예약종료 | IntgSupPlnMngController | ADM | TRES_ORDER_PLAN |
| /suppln/intgSupPlnMng/saveResrvSupPlnAllFin | 예약공급계획 전체예약종료 | IntgSupPlnMngController | ADM | TRES_ORDER_PLAN |
| /suppln/intgSupPlnMng/modifyOrdMkLTSupPln | LT공급계획리드타임(주문제작권한등록) 수신 저장 | IntgSupPlnMngController | ADM | TLTPLANQTY |
| /suppln/intgSupPlnMng/modifyLtPlnLeadtm | LT 계획 리드타임 수정 | IntgSupPlnMngController | ADM | TLTPLANQTY |
| /suppln/intgSupPlnMng/retrieveLtSupPlnApplyGbLst | LT공급계획 적용상태 조회 | IntgSupPlnMngController | ADM | DUAL |
| /suppln/intgSupPlnMng/retrieveDailySupPlnBdTmLst | Daily공급계획 방송일자 조회 | IntgSupPlnMngController | ADM | TSALEPLAN, TITEM, TCODE |
| /suppln/intgSupPlnMng/retrieveDailySupPlnQtySum | Daily 공급 계획 공급 수량 합계 조회 | IntgSupPlnMngController | ADM | TSALEPLANDTL |
| /suppln/leadtmBndeMod/retrieveLeadtmBndeModTgt | 리드타임 일괄변경 대상 조회 | LeadtmBndeModController | ADM | TLTPLANQTY, TRES_ORDER_PLAN, TLTPLANQTY_DAILY, TITEM, TITEMLIST_DETAIL, TVENDOR, TBRAND, TITEMKINDS, TCODE |
| /suppln/leadtmBndeMod/registerLeadtmBndeMod | 리드타임 일괄변경 등록 | LeadtmBndeModController | ADM | TCODE, TLT_CHNG_TMP |
| /suppln/offlineReintlk/retrieveOfflineIntlkErr | 오프라인 연동오류 조회 | OfflineReintlkController | ADM, EAI_IMS | TITEM_OFFLINE_MAPP, TITEMORG_BOX, TITEM, TITEMPRICE, TITEMCHNL, TOFFLINE_ITEM_PRC, EAI_IMS.TOFFLINE_ITEM_PRC_IF, TITEM_ERR_MSG, TCODE, TCHANNEL |
| /suppln/offlineReintlk/registerOfflineItemPrc | 오프라인포스재연동 등록 | OfflineReintlkController | ADM, EAI_IMS | TOFFLINE_ITEM_PRC, EAI_IMS.TOFFLINE_ITEM_PRC_IF |
| /suppln/prdOrderApi/retrieveCounOrderCnt | 컨택주문건수조회 | PrdOrderApiController | ADM | TCOUNORDER |
| /suppln/prdOrderApi/retrieveOrderItemQty | 주문상품수량조회 | PrdOrderApiController | ADM | TORDERITEM |
| /suppln/prdOrderApi/retrieveOrderAggtQty | 주문집계수량조회 | PrdOrderApiController | ADM | TORDAGGT |
| /suppln/realStockSupPlnMng/retrieveRealStockSupPlnModTgt | 실재고 공급계획변경대상 조회 | RealStockSupPlnMngController | ADM | TITEM, TITEMVEN, TITEMCHNL, TITEMADD, TITEMLIST_DETAIL, TCODE, TDEPT, TMD, TBUYMD |
| /suppln/realStockSupPlnMng/retrieveItemStockSttus | 단품재고현황 조회 | RealStockSupPlnMngController | ADM | TITEM, TITEMVEN, TITEMORG_BOX, TITEMCHNL |
| /suppln/realStockSupPlnMng/registerRealStockSupPlnMod | 실재고공급계획 변경등록 | RealStockSupPlnMngController | ADM | TITEM, TITEMVEN, TSALEPLAN, TRES_ORDER_PLAN, TLTPLANQTY, TITEMORG_BOX, TCODE |
| /suppln/realStockSupPlnMng/retrieveRealStockSupPln | 실재고공급계획조회 | RealStockSupPlnMngController | ADM | TITEMCHNL, TITEMVEN, TITEMORG_BOX, TITEM, TCODE, TWAREHOUSE, TVEN, TMD, TDEPT, TITEMLIST_DETAIL, TITEMADD, TMDLINK |
| /suppln/realStockSupPlnMng/retrieveRealStockSupPlnDtl | 실재고공급계획상세조회 | RealStockSupPlnMngController | ADM | TITEMCHNL, TITEMVEN, TITEMORG_BOX, TITEM, TCODE, TWAREHOUSE, TMD, TDEPT |
| /suppln/shipInstPreplnDdCalcApi/retrieveShipInstPreplnDdCalc | 출하예정일계산 | ShipInstPreplnDdCalcApiController | ADM | TITEMCHNL, TLOGIHDAY |