| 대상 엔드포인트 또는 서비스명 | 주석내용 요약 | 데이터베이스 | 테이블 |
|---|---|---|---|
| ApiTestController./retrieveOrdCnclSvcTgtChk.action | 주문 취소 가능 여부를 확인합니다. | ADM | TORDERDTL |
| ApiTestController./retrieveOrdCnclRetngSvcTgtChk.action | 주문 반품/취소 가능 여부를 확인합니다. | ADM | TORDERDTL |
| ApiTestController./saveArsOrdCncl.action | ARS 주문 취소를 처리합니다. | ADM | TORDERDTL |
| ApiTestController./retrieveMultipleItemOrdValidation.action | 다중 품목 주문에 대한 유효성을 검색합니다. | ADM | TORDERDTL |
| ApiTestController./retrieveSmsSendHpNoAsArs.action | SMS 발송을 위한 고객의 휴대폰 번호를 검색합니다. | ADM | TCUSTADDR |
| ArsOrderCreateController./createOrder | ARS 주문을 생성합니다. | ADM | TORDERMASTER, TORDERITEM, TORDERDTL, TINAMT, TRECEIVER, TOFFER, TCUSTDCCOUPON, TCJDC_MEMBER, TGRADEDCBAS |
| CustomerController./InquireTelephoneCustomerAction.action | 전화번호로 고객 정보를 조회합니다. | ADM | TCUSTADDR, TCUSTOMER |
| CustomerController./InquireResidentCustomerAction.action | 주민등록번호로 고객 정보를 조회합니다. | ADM | TCUSTOMER, TCUSTADDR |
| CustomerController./StoreArsContactInformationAction.action | ARS 접촉 정보를 저장합니다. | ADM | TCONTACT |
| CustomerController./InquireArsContactNumberAction.action | ARS 접촉 번호를 조회하고 생성합니다. | ORD | Q_CONTACT_NO, Q_CONTACT_NO_MON, Q_CONTACT_NO_TUE, Q_CONTACT_NO_WED, Q_CONTACT_NO_THU, Q_CONTACT_NO_FRI, Q_CONTACT_NO_SAT |
| CustomerController./InquireGetCJmallIdInfoYnAction.action | 고객이 CJmall ID를 가지고 있는지 확인합니다. | ADM | TCUSTOMER, TCUSTADDR, TCUSTCONVINTERNET |
| CustomerController./InquireGetCjMemberYnAction.action | 고객이 임직원인지 확인합니다. | ADM | TCJMEMBER, TGRPCOMP |
| CustomerController./StoreArsRestingCustomerRestoreAction.action | 휴면 고객을 복구합니다. | ADM | TCUSTOMER, TCUSTOMER_DIV_HIST |
| CustomerController./DownloadCustCopAction.action | 고객에게 쿠폰 발급을 처리합니다. | ADM | TOFFERCUST, TOFFER, TFSBDCCOUPON, TCUSTDCCOUPON, TITEMPRICE, TITEM, TITEMCHNL |
| CustomerController./InquireArsCustSaveAmtAction.action | 고객의 적립금을 조회합니다. | ADM | TSAVEAMTREM |
| CustomerController./InquireArsCjomMemberQAction.action | CJ ONE 멤버십 및 포인트 정보를 조회합니다. | ADM | TCODE, TCUSTOMER, TCUSTOMER_OM, TINAMT, TCBCODE |
| CustomerController./InquireCustomerSaveAmountAction.action | 고객의 적립금을 조회합니다. | ADM | TSAVEAMTREM |
| CustomerController./InquireLastPaymentMethodAction.action | 고객의 마지막 결제 수단을 조회합니다. | ADM | TINAMT, TCUSTPAM, TBANK, TCARD, TCARDDTL |
| CustomerController./InquireBanksAccountAction.action | 가상 계좌 정보를 조회합니다. | ADM | TVIRTUALACNT, TBANK, TCBCODE |
| CustomerController./InquireArsBirthdayAgreeAction.action | ARS 생년월일 동의 여부를 조회합니다. | ADM | TCUSTPAM, TCARDDTL, TCUSTCAGREE |
| CustomerController./InquireDepositorAction.action | 예금주 이름을 조회합니다. | ADM | (외부 API 호출) |
| CustomerController./InquireRecentUseBankAction.action | 최근 사용한 은행 정보를 조회합니다. | ADM | TINAMT, TCBCODE, TBANK |
| CustomerController./InquireAnoRecentUseBankAction.action | 다른 최근 사용 은행 정보를 조회합니다. | ADM | TINAMT, TCBCODE, TBANK |
| CustomerController./StoreNewAddrAction.action | 새로운 고객 배송지를 추가합니다. | ADM | TRECEIVER, TRECEIVER_FB |
| CustomerController./StoreArsOrderNumberAction.action | 주문 저장 후 주문 번호와 고객 번호를 업데이트합니다. | ADM | TARSRECEIVER |
| CustomerController./StoreCustomerDeliveryAddressAction.action | 고객 배송지를 저장합니다. | ADM | TRECEIVER, TRECEIVER_FB, TCUSTADDR |
| CustomerController./InquireArsCustMarketAgreeAction.action | ARS 고객 마케팅 동의 여부를 조회합니다. | ADM | TCUSTMAGREE, TCODE |
| CustomerController./StoreArsCustMarketAgreeAction.action | ARS 고객 마케팅 동의 여부를 저장합니다. | ADM | TCUSTMAGREE, TCODE |
| CustomerController./StoreCellNoAction.action | 고객의 휴대폰 번호를 변경합니다. | ADM | TCUSTADDR, TRECEIVER, TVOC |
| CustomerController./InquireInsuranceCommentAgreeAction.action | 보험 마케팅 동의 여부를 조회합니다. | ADM | TCUSTMAGREE, TSMSREJECT |
| CustomerController./StoreTelephoneNonCustomerAction.action | 렌탈/보험 주문을 위한 비회원 고객을 생성합니다. | ADM | TCUSTADDR, TRECEIVER, TCUSTOMER, TSEGCUST, TCUSTRCVHIST |
| CustomerController./InquireGetLimitCounOrdYnAction.action | ARS 상담 상품 주문 제한 여부를 확인합니다. | ADM | TCOUNORDER, TBDSCHEDTL, TITEM |
| CustomerController./InquireGetNonMemberYnAction.action | 고객이 비회원인지 확인합니다. | ADM | TCUSTOMER |
| CustomerController./InquireNonMemberTokenAction.action | 비회원 인증 토큰을 생성합니다. | ADM | TCBCODE |
| CustomerController./StoreSmsReceiveDenyAction.action | SMS 수신 거부 정보를 저장합니다. | ADM | TSMSREJECT, TCUSTOMER, TCUSTADDR |
| CustomerController./StoreSmsReceiveDenyByCellNoAction.action | 휴대폰 번호로 SMS 수신 거부 정보를 저장합니다. | ADM | TSMSREJECT, TCUSTOMER, TCUSTADDR |
| CustomerController./InquireArsDissCustomerAction.action | ARS 고객 정보를 확인하고 저장합니다. | ADM | TCUSTOMER, TARSSURVEY |
| CustomerController./InquireArsDissCustomerFirQuestAction.action | 첫 번째 ARS 설문 질문을 조회합니다. | ADM | TSURVEYQUEST |
| ItemController./InquireBroadcastItemNameValidationAction.action | 위성/방송 상품명 유효성을 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM |
| ItemController./InquiretTotalBroadcastItemNameValidationAction.action | 전체 방송 상품명 유효성을 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM |
| ItemController./InquireBeforeBroadcastItemAction.action | 이전 방송 상품 정보를 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM, TBDSCHEDTL_BEF |
| ItemController./InquireArsItemInformationAction.action | ARS 상품 및 품목 정보를 조회합니다. | ADM | TITEM, TITEMCHNL, TITEMVEN, TDELIVCST, TITEMADD, TBDSCHEDTL, TCOUNORDERSTATUS |
| ItemController./InquireItemValidationAction.action | 중복 주문을 확인합니다. | ADM | (외부 API 호출) |
| ItemController./InquireItemMentApplyYesNoAction.action | 상품에 멘트 적용 여부를 조회합니다. | ADM | (함수 호출) |
| ItemController./InquireAnotherItemSaleMediumAction.action | 다른 상품 판매 매체를 조회합니다. | ADM | TCBCODE |
| ItemController./InquireArsRealCatalogCodeInformationAction.action | ARS 실제 카탈로그 코드 정보를 조회합니다. | N/A | N/A |
| ItemController./InquireShowhostInfomationAction.action | 현재 방송 중인 쇼호스트 정보를 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM, TBDSCHEDTLMAN, TPERINFO, TPLAYER |
| ItemController./InquireItemCodeUnitAction.action | 상품 및 단위 코드 정보를 조회합니다. | ADM | TITEM, TITEMCHNL, TDIFF_IDS, TDIFF_GROUP_DETAIL, TITEMARSSEQ, TPROMOFFERUNIT, TPROMDTL, TPROMOFFER, TPROM |
| ItemController./InquireDeliveryInvalidAreaYesnoAction.action | 배송 불가 지역을 조회합니다. | ADM | (외부 API 호출) |
| ItemController./InquireSetItemCheckAction.action | 세트 상품 정보를 확인합니다. | ADM | TITEM, TPACK, TITEMCHNL |
| ItemController./InquireItemCodePackAction.action | 세트 상품 구성 세부 정보를 조회합니다. | ADM | TITEM, TPACK, TITEMCHNL, TITEMVEN, TDELIVCST, TITEMADD, TDIFF_IDS, TDIFF_GROUP_DETAIL, TITEMARSSEQ |
| ItemController./StoreArsPassItemAlimiAction.action | ARS 상품 알림 서비스를 저장합니다. | ADM | (외부 API 호출) |
| ItemController./StoreArsVocAction.action | ARS VOC를 생성합니다. | ADM | TVOC |
| ItemController./InquireGetDirectPurchaseYnAction.action | 해외 직구 상품 상태를 조회합니다. | ADM | TCBCODE, TITEM |
| ItemController./StoreInsuranceItemOrderAction.action | 보험 상품 주문을 저장합니다. | ADM | TCOUNORDER, TITEM, TBDSCHEDTL, TITEMADD, TVENDOR, TITEMCHNL |
| ItemController./InquireFundItemSettingValidAction.action | 펀드 상품 설정 유효성을 조회합니다. | ADM | TCUSTMAGREE, TCUSTADDR |
| ItemController./InquireSpecifyInsuranceItemMentAction.action | 특정 보험 상품 멘트를 조회합니다. | ADM | TITEMCHNL, TVENDOR, TITEM, TITEMADD, TCOUNORDERSTATUS |
| ItemController./InquireBeforeInsuranceItemSettingAction.action | 보험 상품 설정 전 정보를 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM, TBDSCHE, TITEMARSSEQ, TBDSCHEDTL_BEF |
| ItemController./InquireBeforeContactItemAction.action | 컨택 전 상품 정보를 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEMCHNL, TITEM |
| OrderController./InquireSaleMediumAction.action | 판매 매체 정보를 조회합니다. | N/A | N/A |
| OrderController./InquireReturnItemDeliveryAmountAction.action | 반품 배송비를 조회합니다. | ADM | TITEMCHNL |
| OrderController./InquireCustomerReturnAction.action | 고객 반품 정보를 조회합니다. | ADM | TITEMCHNL |
| OrderController./InquireUnitItemCodeAction.action | 단위 품목 코드를 조회합니다. | ADM | TITEM, TITEMCHNL, TDIFF_IDS, TPROMOFFERUNIT, TPROMDTL, TPROMOFFER, TPROM, TITEMVEN |
| OrderController./InquireWorkSequenceAction.action | 작업 시퀀스 번호를 조회합니다. | ADM | (외부 API 호출) |
| OrderController./InquireDeliveryPlanDateAction.action | 배송 계획 날짜를 조회합니다. | ADM | (외부 API 호출) |
| OrderController./InquireAccountBankNumberAction.action | 계좌 은행 번호를 조회합니다. | ADM | (외부 API 호출) |
| OrderController./StoreCounselQuantityAction.action | 주문/프로모션 선점 수량을 해제합니다. | ADM | (외부 API 호출) |
| OrderController./InquireCjdcMemberInfoAction.action | 임직원 할인 정보를 조회합니다. | ADM | TCJMEMBER, TGRPCOMP, TCJDC_MEMBER, TCJCOMP_CARD |
| OrderController./InquireGradeDcBaseInfoAction.action | VIP 선할인 기본 정보를 조회합니다. | ADM | TSEGCUST, TGRADEDCBAS |
| OrderController./InquireGradeDcExceptAction.action | VIP 선할인 예외 상품을 조회합니다. | ADM | TITEMPRICE, TITEM, TITEMCHNL, TCBCODE, TSEGCUST, TGRADEDCBAS |
| OrderController./InquireCjOnePointApprovalImpossibleAction.action | CJ ONE 포인트 결제 불가 여부를 확인합니다. | ADM | TITEM, TITEM_PAY_CTRL_ATTR |
| OrderController./InquireCardOrCashApprovalImpossibleAction.action | 카드/현금 결제 불가 여부를 확인합니다. | ADM | TITEM, TITEM_PAY_CTRL_ATTR |
| OrderController./StoreVirtualAccountAction.action | 가상 계좌 정보를 저장합니다. | ADM | TVIRTUALACNT |
| OrderController./InquireCardEmbezzlementAction.action | 카드 횡령 여부를 확인합니다. | ADM | TCARDCHKNO, TORDERLIMITCARD, TSAMCARD, TCUSTPAM, TCARDDTL |
| OrderController./InquireCardApprovalErrorCountAction.action | 카드 승인 오류 횟수를 조회합니다. | ADM | TCUSTPAM, TCUSTOMER |
| OrderController./InquireCashApprovalImpossibleAction.action | 현금 결제 불가 여부를 확인합니다. | ADM | TITEM, TITEM_PAY_CTRL_ATTR |
| OrderController./InquireFashionOrderImpossibleAction.action | 패션 상품 주문 불가 여부를 확인합니다. | ADM | TITEM, TPACK, TSEGCUST |
| OrderController./InquireSaveAmtApprovalImpossibleAction.action | 적립금 결제 불가 여부를 확인합니다. | ADM | TITEM, TITEM_PAY_CTRL_ATTR |
| OrderController./InquireImpossibleNorestCardAction.action | 무이자 할부 불가 카드 정보를 조회합니다. | ADM | TCARDCHKNO, TORDERLIMITCARD, TSAMCARD, TCUSTPAM, TCARDDTL, TCBCODE |
| OrderController./InquireCardNumberValidationAction.action | 카드 번호를 유효성 검사합니다. | ADM | TCBCODE, TCARDDTL |
| OrderController./InquireNorestAllotmentMonthAction.action | 무이자 할부 개월 수를 조회합니다. | ADM | TCUSTPAM, TCARDDTL, TCBCODE |
| OrderController./InquireCardBankTerminalIdAction.action | 카드 은행 터미널 ID를 조회합니다. | ADM | TCARDDTL |
| OrderController./InquireCustHPNumberAction.action | 고객의 휴대폰 번호를 조회합니다. | ADM | TCUSTADDR |
| OrderController./InquireDeliveryPlaceAction.action | 신규/기존 배송지를 조회합니다. | ADM | TRECEIVER, TCUSTOMER, TCUSTADDR, TCODE |
| OrderController./InquireDeliveryPlanGuideAction.action | 배송 계획 가이드를 조회하고 카카오톡 알림을 발송합니다. | ADM | TBDSCHEDTL, TITEM, TITEMCHNL |
| OrderController./StoreCardApprovalErrorCountAction.action | 카드 승인 오류 횟수를 저장합니다. | ADM | TCUSTPAM |
| OrderController./InquireReceiptAccountAction.action | 영수증 계좌 정보를 조회합니다. | ADM | TVIRTUALACNT |
| OrderController./StoreOrderAction.action | 주문을 저장합니다. | ADM | TORDERMASTER, TORDERITEM, TORDERDTL, TINAMT, TRECEIVER, TOFFER, TCUSTDCCOUPON, TCJDC_MEMBER, TGRADEDCBAS |
| OrderController./StoreDeliveryPlaceAction.action | ARS 배송지를 수정합니다. | ADM | TORDERDTL |
| OrderController./InquireOrderValidationAction.action | 주문을 유효성 검사합니다. | ADM | TORDERITEM, TORDERDTL, TINAMT |
| OrderController./RequestArsCardApprovalAction.action | ARS 카드 승인을 처리합니다. | ADM | TCUSTPAM, TCARDDTL, TCARDCHKNO, TCUSTCAGREE |
| OrderController./InquireCjCardYesnoAction.action | CJ 카드 여부를 확인합니다. | ADM | TCJCARD |
| OrderController./InquireDeliverySequenceAction.action | 배송 시퀀스 번호를 조회합니다. | ADM | TRECEIVER |
| PromotionController./InquireArsPromotionInformationAction.action | ARS 프로모션 정보를 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEM, TITEMCHNL, TLUMPSUMM, TPROMOFFERUNIT, TPROMDTL, TPROMOFFER, TPROM |
| PromotionController./InquirePromotionItemInformationAction.action | 프로모션 상품 정보를 조회합니다. | ADM | TITEM, TITEMCHNL, TITEMVEN |
| PromotionController./InquirePromotionAmtAction.action | 프로모션 금액을 조회합니다. | ADM | TPROMOFFER |
| PromotionController./InquireDiscountPriceAction.action | 할인 가격을 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEM, TITEMCHNL, TLUMPSUMM, TPROMOFFERUNIT, TPROMDTL, TPROMOFFER, TPROM |
| PromotionController./InquireLumpDiscountAmountAction.action | 일괄 할인 금액을 조회합니다. | ADM | TCODE, TBDSCHEDTL, TITEM, TITEMCHNL, TLUMPSUMM, TPROMOFFERUNIT, TPROMDTL, TPROMOFFER, TPROM |
| PromotionController./StoreInsuranceMarketingReceiveAction.action | 보험 마케팅 수신 정보를 저장합니다. | ADM | TCUSTMAGREE, TSMSREJECT |
| PromotionController./StoreSmsMktReceiveDenyAction.action | SMS 마케팅 수신 거부 정보를 저장합니다. | ADM | TSMSREJECT, TCUSTOMER, TCUSTADDR, TCUSTMAGREE |
| PromotionController./InquireGiftWinAction.action | 경품 당첨 정보를 조회합니다. | ADM | (외부 API 호출) |
| PromotionController./InquireOrderOneShotDcAction.action | 고객의 일회성 할인을 조회합니다. | ADM | TCUSTDCCOUPON, TOFFER, TITEMPRICE, TITEM, TITEMCHNL |
| PromotionController./InquireOrderOneShotDcListAction.action | 고객의 일회성 할인 목록을 조회합니다. | ADM | TCUSTDCCOUPON, TOFFER, TITEMPRICE, TITEM, TITEMCHNL |
| PromotionController./InquireOrderCouponAction.action | 고객 쿠폰 정보를 조회합니다. | ADM | TCUSTDCCOUPON, TOFFER, TITEMPRICE, TITEM, TITEMCHNL |
| PromotionController./InquireOrderCouponListAction.action | 고객 쿠폰 목록을 조회합니다. | ADM | TCUSTDCCOUPON, TOFFER, TITEMPRICE, TITEM, TITEMCHNL |