# or-pay-svc

| 대상 엔드포인트 또는 서비스명 | 주석 내용 요약(한글로 출력) | 컨트롤러 명 | 데이터베이스 | 테이블 |
|---|---|---|---|---|
| /payment/paid-amounts | 적립금/방송상품지원금 | PaidAmountController | MyBatis | TB_PAID_AMOUNT |
| /acctmng/bankAcnt/retrieveInamtBankSeqAcnt | InquireReceiptAccountAction 입금은행순번/계좌조회 inquireReceiptAccount | BankAcntController | MyBatis | TB_INAMT_BANK |
| /acctmng/bankAcnt/retrieveCustInamtBankAcntLst | 고객별 가상계좌번호 조회  ( 기부여 가상계좌 + 미부여 은행목록 + 일반계좌 )- path(/account/list-customer-paymentinfo.json) 참조 - ( TINAMTBANK --입금은행 TVIRTUALACNT --가상계좌 ) | BankAcntController | MyBatis | TB_VIRTUAL_ACCOUNT |
| /acctmng/bankAcnt/giveVirtAcnt | 1. 고객별 가상계좌번호 조회 ( TINAMTBANK--입금은행, TVIRTUALACNT --가상계좌 ) 2. 무통장 입금 계좌생성 | BankAcntController | MyBatis | TB_VIRTUAL_ACCOUNT |
| /acctmng/bankAcnt/updateCnclCustVirtAcntGive | 상품파트 사용 | BankAcntController | MyBatis | TB_VIRTUAL_ACCOUNT |
| /acctmng/bankAcnt/retrieveVirtAcnt | 입금관리 > 가상계좌조회 | BankAcntController | MyBatis | TB_VIRTUAL_ACCOUNT |
| /acctmng/bankAcnt/registerBankCd | 은행코드등록 | BankAcntController | MyBatis | TB_BANK |
| /acctmng/bankAcnt/retrieveBankLst | 은행코드, 명, 사용유무, 환불가능여부 | BankAcntController | MyBatis | TB_BANK |
| /acctmng/bankAcnt/retrieveBankbyAcntLst | 은행코드, 계좌타입, 계좌번호, 지점명, 주소, 담당자n | BankAcntController | MyBatis | TB_INAMT_BANK |
| /acctmng/bankAcnt/registerBankbyAcnt | 은행코드, 계좌타입, 계좌번호, 지점명, 주소, 담당자 | BankAcntController | MyBatis | TB_INAMT_BANK |
| /paycmmn/settlebank/approveSettlebank | @ this.procOrderInAmount_Settlebank(orderInAmountCjMallDto)<br>settlebankServiceModule.createSettlebankApproval(settlebankApprovalCriteria) -> API | SettlebankController | MyBatis | TB_SETTLEBANK_APPROVAL, TB_SETTLEBANK_LOG |
| /paycmmn/settlebank/certifySettlebank | @ getOrderCetainSettlebankResponseTuple  <br>/order/settlebank-ready.json <br><br>인증인데 따로 통신하는건 없음 로그만 등록 | SettlebankController | MyBatis | TB_SETTLEBANK_APPROVAL, TB_SETTLEBANK_LOG |
| /paycmmn/settlebank/cancelSettlebank | @ /order/settlebank-cancel.json | SettlebankController | MyBatis | TB_SETTLEBANK_APPROVAL, TB_SETTLEBANK_LOG |
| /paycmmn/smilepay/certifySmilepay | 동일한 데이터 인증인 경우 500에러 발생함, 테스트시 다른값 입력필요 | SmilepayController | MyBatis | TB_SMILEPAY_APPROVAL, TB_SMILEPAY_LOG |
| /paycmmn/smilepay/approveSmilepay | 스마일페이승인 | SmilepayController | MyBatis | TB_SMILEPAY_APPROVAL, TB_SMILEPAY_LOG |
| /paycmmn/smilepay/cancelSmilepay | 스마일페이취소 | SmilepayController | MyBatis | TB_SMILEPAY_APPROVAL, TB_SMILEPAY_LOG |
| /paycmmn/syrupPay/approveSyrupPay | 시럽결제승인 | SyrupPayController | MyBatis | TB_SYRUP_APPROVAL, TB_SYRUP_LOG |
| /paycmmn/syrupPay/certifySyrupPay | 시럽결제인증 | SyrupPayController | MyBatis | TB_SYRUP_APPROVAL, TB_SYRUP_LOG |
| /paycmmn/syrupPay/cancelSyrupPay | 시럽결제취소 | SyrupPayController | MyBatis | TB_SYRUP_APPROVAL, TB_SYRUP_LOG |
| /paycmmn/tvPay/approveTvpay | TVPAY승인 | TvPayController | MyBatis | TB_TVPAY_APPROVAL, TB_TVPAY_LOG |
| /paycmmn/tvPay/cancelTvpay | TVPAY취소 | TvPayController | MyBatis | TB_TVPAY_APPROVAL, TB_TVPAY_LOG |
| /paycmmn/tvPay/registerTvpayAppvRead | TVPay승인조회등록 | TvPayController | MyBatis | TB_TVPAY_APPROVAL, TB_TVPAY_LOG |
| /payment/points/celeb-shop/aggregated-by-date | 셀렙샵 적립금 일자별 집계표 조회 | CelebShopPointController | MyBatis | TB_CELEBSHOP_POINT_DAILY_AGGREGATED |
| /payment/points/celeb-shop/review | 셀렙샵 상품 리뷰 작성에 따른 적립금을 지급한다. | CelebShopPointController | MyBatis | TB_CELEBSHOP_REVIEW_POINT |
| /payment/points/celeb-shop/order | 셀렙샵 회원 등급별 주문금액에 대해 적립금을 지급한다, | CelebShopPointController | MyBatis | TB_CELEBSHOP_ORDER_POINT |
| /payment/points/{custNo}/summary | 사용가능한 적립금, 소멸예정 적립금, 다운로드가능 적립금액 조회사용 API : https://base-api.cjoshopping.com/api/external/savedMoney/savedMoney.json?customerNo= | EcBaseLegacyPointController | MyBatis | TB_CALCULATED_POINT_BALANCE |
| /payment/points/{custNo}/used | 사용 API : https://base.cjonstyle.com/c/rest/savedMoeny/savedMoneyDetailList?month=-1 | EcBaseLegacyPointController | MyBatis | TB_POINT_STATUS_BY_OFFER |
| /payment/empdc/retrieveDiscount | 임직원 선할인 한도 조회 (연도별 grouping) | EmployeeDiscountController | MyBatis | 존재하지 않음 |
| /payment/empdc/retrieveMainDiscountDetail | 주 한도에 대한 사용내역 조회 (특정년도 조회) | EmployeeDiscountController | MyBatis | 존재하지 않음 |
| /payment/empdc/retrieveSubDiscountDetail | 부 한도에 대한 사용내역 조회 (특정년도 조회) | EmployeeDiscountController | MyBatis | 존재하지 않음 |
| /payment/points-adjustment/validation | 적립금 유효성 검증 | PointAdjustmentController | MyBatis | TB_POINT_ADJUSTMENT |
| /payment/points-adjustment/validation-count | 적립금 유효성 검증 건수 | PointAdjustmentController | MyBatis | TB_POINT_ADJUSTMENT |
| /payment/points-adjustment/move/cj-card | CJ카드 적립금 이관 | PointAdjustmentController | MyBatis | 존재하지 않음 |
| /payment/points-adjustment/predicated-balances | 적립금 추정 잔액 조회 | PointAdjustmentController | MyBatis | 존재하지 않음 |
| /payment/points/aggregated-by-date | 온스타일 적립금 일자별 집계표 조회 | PointController | MyBatis | TB_ONSTYLE_POINT_DAILY_AGGREGATED |
| /payment/points/undo | 고객번호, 적립금 부여 순번 기준 부여건을 취소처리한다. | PointController | MyBatis | 존재하지 않음 |
| /payment/points/redo | 고객번호, 적립금 부여 순번 기준 취소된 적립금을 원복한다. | PointController | MyBatis | 존재하지 않음 |
| /payment/points/undo-by-offer | 동일한 오퍼코드를 통해 부여된 적립금을 전부 취소처리한다. | PointController | MyBatis | 존재하지 않음 |
| /payment/points/deadline | 오퍼코드로 부여된 적립금의 유효종료일자를 요청한 유효종료일자로 변경한다.요청한 유효종료 일자가 없는 경우 오퍼코드에 등록된 유효종료일자로 변경한다. | PointController | MyBatis | TB_HSAVEAMT_GIVE |
| /payment/points/{custNo}/calculate-balance | 오퍼코드, 조회시작일자, 조회종료일자를 통해 고객의 적립금 사용현황을 조회한다. | PointController | MyBatis | TB_CALCULATED_POINT_BALANCE |
| /payment/points/calculate-balance | 고객번호를 통해 적립금 내역을 계산한 잔액으로 적립금 잔액 데이터를 재조정한다. | PointController | MyBatis | TB_POINT_BALANCE |
| /payment/points/status-list | 오퍼코드, 조회시작일자, 조회종료일자를 통해 고객의 적립금 사용현황을 조회한다. | PointController | MyBatis | TB_POINT_STATUS_BY_OFFER |
| /payment/points/adjust-minus | 가용적립금이 마이너스인 고객의 적립금을 사용가능한 상태로 보정처리한다. | PointController | MyBatis | TB_POINT_ADJUSTMENT |
| /payment/points/use | 고객번호, 주문번호, 적립금액을 통해 고객의 가용적립금에서 사용처리한다. | PointController | MyBatis | TB_SAVEAMT_USE, TB_SAVEAMT_USE_V2 |
| /payment/points/undo-used | 고객번호, 주문번호 통해 사용된 적립금을 사용 취소 처리한다. | PointController | MyBatis | TB_SAVEAMT_USE |
| /payment/points/campaign | 오퍼코드를 통해 캠폐인 적립금을 지급한다. | PointController | MyBatis | TB_CAMPAIGN_POINT |
| /payment/points/campaign/allow-duplicates | 동일 고객에게 오퍼코드를 통해 캠폐인 적립금 지급시 오퍼코드 중복 허용 하여 지급한다. | PointController | MyBatis | TB_CAMPAIGN_POINT |
| /payment/points/refund | 고객번호, 환급금액을 통해 고객의 카드 가용적립금에서 환급처리한다. | PointController | MyBatis | TB_SAVEAMT_USE |
| /payment/points/download/point-row/{yyyyMM} | 지정된 년월의 적립금 지급 및 사용 내역에 대한 다운로드 가능한 pre-signed URL을 생성합니다. | PointDownloadController | MyBatis | TB_HSAVEAMT_GIVE, TB_SAVEAMT_USE |
| /payment/benefits/vip/discount | 고객번호별 VIP 선할인을 생성한다. | VipDiscountController | MyBatis | TB_VIP_DISCOUNT |
| /payment/benefits/vip/discount/{offerCd}/count | 오퍼코드별 VIP 선할인 갯수를 조회한다. | VipDiscountController | MyBatis | TB_VIP_DISCOUNT |
| /refundmng/cashRefund/registerCashRefundRcpt | 적립금환불/현금입금환불 접수1. 적립금부여취소 : requestSaveAmountGiveOrder2. 현금환불접수등록(TREPAY)3. 현금입금연결처리(TCMSLINK) : 09오류4. 현금입금완료(TCMS) : 연결구분='70' | CashRefundController | MyBatis | TB_CASH_REFUND_RECEIPT |
| /refundmng/cashRefund/retrieveCashRefundRcptLst | 현금환불접수내역조회(TREPAY) : 10접수/10일반환불 | CashRefundController | MyBatis | TB_CASH_REFUND_RECEIPT_LIST |
| /refundmng/cashRefund/transferCashRefundRcpt | 1. 환불정보변경(TREPAY) : 예금주, 환불은행, 계좌번호 외 2. 환불진행단계변경 : 20이관 | CashRefundController | MyBatis | TB_CASH_REFUND_RECEIPT |
| /refundmng/cashRefund/certifyRefundAcnt | f_AcntCert 참조1. 기 인증건이 있는지 체크(3개월이내에 인증 받은것만 유효한 것으로 인식한다)2. 일 첫 요청인지를 체크 -> 개시전문 발송 : accountAuthenticateUtil.sendStartPacket(key);3. 계좌인증요청 : accountAuthenticateUtil.accountCheck();4. 계좌인증요청 등록(TACCTCHECK)5. 계좌인증결과 수정(TACCTCHECK) @Path(\/bank-account-certification\) 참조 | CashRefundController | MyBatis | TB_ACCOUNT_CERTIFICATION |
| /refundmng/cashRefund/retrieveRefundBank | 환불은행팝업조회 | CashRefundController | MyBatis | TB_REFUND_BANK |
| /refundmng/cashRefund/retrieveRefundProcTgt | 환불처리대상조회 | CashRefundController | MyBatis | TB_REFUND_PROCESS_TARGET |
| /refundmng/cashRefund/createCashRefundTgtEdi | 환불관리>환불대상EDI생성<br>팝업 : 이체의뢰일자/이체지정일자 설정<br>환불처리EDI파일생성 : f_Edi_fileCreate참조 | CashRefundController | MyBatis | 존재하지 않음 |
| /refundmng/cashRefund/retrieveRefundProcTgtRst | 환불처리대상결과조회 | CashRefundController | MyBatis | TB_REFUND_PROCESS_RESULT |
| /refundmng/cashRefund/uploadCashRefundProcTgtRst | 환불관리>현금환불처리대상결과UploadStoreRepayMoneyProcessObjectEDIResultAction 참조 | CashRefundController | MyBatis | 존재하지 않음 |
| /refundmng/cashRefund/retrieveRefundCmpltSttus | 환불완료현황 조회(TREPAY) : PROG_CD = '90' | CashRefundController | MyBatis | TB_REFUND_COMPLETE_STATUS |
| /refundmng/cashRefund/retrieveRefundDpstamtTranLst | 환불예치금이관현황 조회(TREPAY) : PROG_CD = '91' | CashRefundController | MyBatis | TB_REFUND_DEPOSIT_TRANSFER |
| /refundmng/cashRefund/retrieveRefundFixAggcht | 환불구분별 확정집계표 출력(TREPAY) : PROG_CD = '90' | CashRefundController | MyBatis | TB_REFUND_FIX_AGGREGATE |
| /refundmng/cashRefund/retrieveRecentRefundHist | 1.입금내역 조회(TCMS)2. 환불내역 조회(TREPAY) | CashRefundController | MyBatis | TB_RECENT_REFUND_HISTORY |
| /refundmng/cashRefund/cancelCashRefundRcpt | 현금환불접수취소 | CashRefundController | MyBatis | TB_CASH_REFUND_RECEIPT |
| /refundmng/cashRefund/retrievePrerefundRcptTgtLst | 선환불대상선택관리조회 | CashRefundController | MyBatis | TB_PREREFUND_RECEIPT_TARGET |
| /refundmng/cashRefund/savePrerefundTgtChoiceMng | 선환불대상선택관리저장 | CashRefundController | MyBatis | TB_PREREFUND_TARGET_CHOICE |
| /refundmng/cashRefund/retrievePreRefundSpecifics | 선환불대상확정목록조회 | CashRefundController | MyBatis | TB_PRE_REFUND_SPECIFICS |
| /refundmng/cashRefund/savePrerefundTgtFix | 선환불대상확정저장 | CashRefundController | MyBatis | TB_PREREFUND_TARGET |
| /refundmng/cashRefund/createCashRefundTgtFile | 환불관리>현금환불대상파일생성<br>환불처리EDI파일생성 | CashRefundController | MyBatis | 존재하지 않음 |
| /refundmng/cjGiftCard/retrieveCjGiftCardAppvList | 기프트카드 승인 결제정보 조회 | CjGiftCardExceptController | MyBatis | TB_CJ_GIFTCARD_APPROVAL |
| /refundmng/cjGiftCard/modifyCjGiftCardDeactvYn | 기프트카드 결제취소 예외처리 수정 | CjGiftCardExceptController | MyBatis | TB_CJ_GIFTCARD |
| /refundmng/gcRefund/retrieveGcRefundHist | 상품권환불내역 (TREPAY) : 홈쇼핑상품권/CJ상품권-> PAM_METH_CD IN ('09','10') | GcRefundController | MyBatis | TB_GC_REFUND_HISTORY |
| /refundmng/gcRefund/retrieveGcRefundTgt | 상품권환불대상리스트 조회(TREPAY) : 홈쇼핑/CJ상품권, PROG_CD <= '10'접수 | GcRefundController | MyBatis | TB_GC_REFUND_TARGET |
| /refundmng/gcRefund/registerGcRefundFix | * 상품권 환불접수건 확정 처리 1. 현금환불(TREPAY)의 환불정보를 접수로 변경 : PROG_CD = '10'접수 | GcRefundController | MyBatis | TB_GC_REFUND |
| /refundmng/gcRefund/registerGcReConv | * 상품권환불접수건 상품권 재전환 처리 1. 현금환불(TREPAY)의 환불정보를 상품권 전환으로 변경 : PROG_CD = '92'상품권전환, CLAIM_CD = '01'취소2. 기타결제사용(TPAMUSE)에 상품권 전환 정보 등록4. CJ상품권마스터(TGROUPGIFTCERT) 환불정보 UPDATE5. 기타결제잔액(TPAMREM)에 환불정보를 UPDATE | GcRefundController | MyBatis | TB_GC_RECONVERSION |
| /refundmng/ptaxRefund/retrievePtaxInamtLst | 입금내역조회 ( TCMS : 현금입금 )TRN_CLS_CD = '20'  /*거래구분코드*/ LINK_CLS_CD = '00'  /*연결구분코드*/ | PtaxRefundController | MyBatis | TB_PTAX_INAMT_LIST |
| /refundmng/ptaxRefund/retrievePtaxRefundHist | 제세공과금환불OB/예치금확정내역 조회(TPRIZEREPAY) | PtaxRefundController | MyBatis | TB_PTAX_REFUND_HISTORY |
| /refundmng/ptaxRefund/retrievePaybyRsnCd | 환불관리>제세공과금OB/예치금확정>환불구분콤보조회 | PtaxRefundController | MyBatis | TB_PAY_REASON_CODE |
| /refundmng/ptaxRefund/registerPtaxInamtRefundDpstamtConv | 1. 현금환불등록(TREPAY) 2. 현금입금연결(TINAMTLINK) 3. 현금입금수정(TCMS) 4. 제세공과금환불대상등록(TPRIZEREPAY) : PROG_CD = '90' | PtaxRefundController | MyBatis | TB_PTAX_INAMT_REFUND_DEPOSIT_CONVERSION |
| /refundmng/retngRefund/retrievePrerefundUnrecall | 선환불미회수내역 조회(TORDERDTL) : REPAY_PROG_CD ='2' | RetngRefundController | MyBatis | TB_PREREFUND_UNRECALL_LIST |
| /refundmng/retngRefund/retrieveMaulRefundProcHist | 환불관리>수동환불처리내역 | RetngRefundController | MyBatis | TB_MANUAL_REFUND_PROCESS_HISTORY |
| /refundmng/retngRefund/retrieveRetngRcptPrerefundTgt | 반품접수선환불대상 조회(TORDERDTL) : PROG_CD IN ('50','55') | RetngRefundController | MyBatis | TB_RETURN_RECEIPT_PREREFUND_TARGET |
| /refundmng/retngRefund/retrieveCardAcptErrorProcTgt | 카드승인Error처리대상조회(TORDERDTL) : REPAY_PROG_CD = '9' | RetngRefundController | MyBatis | TB_CARD_ACCEPT_ERROR_PROCESS_TARGET |
| /refundmng/retngRefund/retrieveRecallFixRefundTgt | 반품회수확정내역 조회(TORDERDTL) : PROG_CD IN ('60','90') | RetngRefundController | MyBatis | TB_RECALL_FIX_REFUND_TARGET |
| /refundmng/retngRefund/retrieveRefundFixPopup | 환불관리>수동환불통합관리>환불확정팝업>환불확정팝업조회고객정보Dto,주문상세Dto,주문입금Dto,주문환불접수Dto | RetngRefundController | MyBatis | TB_REFUND_FIX_POPUP |
| /refundmng/retngRefund/retrieveRefundDcAmtInfo | As-Is : InquireRepayDcAmtAction | RetngRefundController | MyBatis | TB_REFUND_DC_AMOUNT_INFO |
| /refundmng/retngRefund/sbm_retrieveRefundFixInit | 환불관리>수동환불통합관리>환불확정팝업>환불확정팝업초기값정보조회고객정보Dto,주문상세Dto,주문입금Dto,주문환불접수Dto | RetngRefundController | MyBatis | TB_REFUND_FIX_INITIAL_DATA |
| /refundmng/retngRefund/registerInamtAlt | 입금대체등록 | RetngRefundController | MyBatis | TB_INAMT_ALTERNATE |
| /refundmng/retngRefund/registerPayRefundFix | 회수확정환불내역 저장1. 주문입금환불처리 : storeRepayOrderInAmount2. 주문상세 update : 1일반환불, 2선환불3. 임직원선할인한도복구(TGRADEDCLINK,TCJDC_MEMBER)4. 저단가배송비취소처리(TORDERCOST): 강제로 취소대상건조회하여 취소처리5. 결제부분취소처리(TINAMT_PAR_CNCL)6. Payco결제취소 : storeOrderPaycoUseCancel7. 카카오톡페이결제취소 : storeKakaotalkpayAppvCncl8. 시럽페이결제취소 : storeOrderSyrupUseCancel9. TVPay결제취소 : storeOrderTVPayUseCancel10. KCP증액승인취소 : storeOrderKcpPGCardHub11. 네이버페이결제취소 : storeNaverpayAppvCncl12. 스마일페이결제취소 : storeSmilepayAppvCncl13. 현금간편결제취소 : storeSettlebankAppvCncl14. 차이페이결제취소 : storeChaiPayAppvCncl15. 정기결제취소 : storeRegularPayAppvCncl16. 경품제세공과금 환불 처리 : storePrizeRepay17. 수동환불알림톡발송 : storeClaimMsgProcess | RetngRefundController | MyBatis | TB_PAY_REFUND |
| /refundmng/retngRefund/retrieveCardParCnclYn | 카드부분취소여부조회 | RetngRefundController | MyBatis | TB_CARD_PARTIAL_CANCEL |
| /setlmng/settleCharge/retrieveAffiliateSettleCharge | 제휴 정산 수수료 기준을 조회한다. | AffiliateSettleChargeController | MyBatis | TB_AFFILIATE_SETTLE_CHARGE |
| /setlmng/settleCharge/retrieveAffiliateSettleChargeHist | 제휴 정산 수수료 기준 이력을 조회한다. | AffiliateSettleChargeController | MyBatis | TB_AFFILIATE_SETTLE_CHARGE_HISTORY |
| /setlmng/settleCharge/saveAffiliateSettleCharge | 제휴 정산 수수료 기준을 저장한다. | AffiliateSettleChargeController | MyBatis | TB_AFFILIATE_SETTLE_CHARGE |
| /setlmng/settleCharge/saveAffiliateSettleChargeList | 제휴 정산 수수료 기준을 저장한다.(리스트) | AffiliateSettleChargeController | MyBatis | TB_AFFILIATE_SETTLE_CHARGE |
| /setlmng/cafeteriaMng/retrieveCafeteriaDemandHist | 정산관리>카페테리아청구내역>카페테리아청구내역조회 | CafeteriaMngController | MyBatis | TB_CAFETERIA_DEMAND_HISTORY |
| /setlmng/cafeteriaMng/retrieveCafeteriaDeptDemandAgg | 정산관리>카페테리아청구내역>카페테리아부서별청구집계조회 | CafeteriaMngController | MyBatis | TB_CAFETERIA_DEPARTMENT_DEMAND_AGGREGATE |
| /setlmng/cafeteriaMng/retrieveCafeteriaDemandAgg | 정산관리>카페테리아청구내역>카페테리아청구집계조회 | CafeteriaMngController | MyBatis | TB_CAFETERIA_DEMAND_AGGREGATE |
| /setlmng/cafeteriaMng/retrieveCafeteriaHist | 정산관리>카페테리아이력조회 | CafeteriaMngController | MyBatis | TB_CAFETERIA_HISTORY |
| /setlmng/cafeteriaMng/retrieveGrpcompLst | 정산관리>카페테리아청구내역 > 회사콤보(조회조건) | CafeteriaMngController | MyBatis | TB_GROUP_COMPANY |
| /setlmng/cafeteriaMng/retrieveExcelCafeteriaDemandHist | 카페테리아청구내역조회 대용량 엑셀 저장 | CafeteriaMngController | MyBatis | 존재하지 않음 |
| /setlmng/cardImdDcSetl/retrieveCardImdDcSetlDtl | 카드즉시할인정산합계상세조회 | CardImdDcSetlController | MyBatis | TB_CARD_IMMEDIATE_DISCOUNT_SETTLEMENT_DETAIL |
| /setlmng/cardImdDcSetl/retrieveCardImdDcSetlSum | 카드즉시할인정산합계조회 | CardImdDcSetlController | MyBatis | TB_CARD_IMMEDIATE_DISCOUNT_SETTLEMENT_SUMMARY |
| /setlmng/cjDcMemr/retrieveCjDcMemberDtlStatus | 임직원지원금액상세조회 | CjDcMemrController | MyBatis | TB_CJ_DISCOUNT_MEMBER_DETAIL_STATUS |
| /setlmng/cjDcMemr/retrieveCjDcMemberStatus | 임직원지원금액조회 | CjDcMemrController | MyBatis | TB_CJ_DISCOUNT_MEMBER_STATUS |
| /setlmng/cjOnePntMng/retrieveCjOnePntMngDtl | CJONE포인트관리상세조회 | CjOnePntMngController | MyBatis | TB_CJONE_POINT_DETAIL |
| /setlmng/cjOnePntMng/retrieveCjOnePntMng | CJONE포인트관리조회 | CjOnePntMngController | MyBatis | TB_CJONE_POINT_MANAGEMENT |
| /setlmng/cjOnePntMng/retrieveIntgMemCardNo | 통합회원카드번호조회 | CjOnePntMngController | MyBatis | TB_INTEGRATED_MEMBER_CARD |
| /setlmng/cjOnePntMng/cancelCjOnePntHwrtGive | CJONE포인트수기부여취소 | CjOnePntMngController | MyBatis | TB_CJONE_POINT_MANUAL_GIVE |
| /setlmng/coGc/retrieveCoGcUseSttus | 제휴상품권사용현황조회 | CoGcController | MyBatis | TB_CO_GIFT_CERTIFICATE |
| /setlmng/dpstamtMng/retrieveCustbyDpstamtMng | 고객별예치금관리조회 | DpstamtMngController | MyBatis | TB_CUSTOMER_DEPOSIT_MANAGEMENT |
| /setlmng/dpstamtMng/retrieveDpstamtOccHist | 예치금발생내역조회 | DpstamtMngController | MyBatis | TB_DEPOSIT_OCCURRENCE_HISTORY |
| /setlmng/dpstamtMng/registerDpstamtTran | 예치금이관등록 | DpstamtMngController | MyBatis | TB_DEPOSIT_TRANSFER |
| /setlmng/dpstamtMng/retrieveDpstamtDtbyUseSttus | 예치금일자별사용현황조회 | DpstamtMngController | MyBatis | TB_DEPOSIT_DAILY_USE_STATUS |
| /setlmng/dpstamtMng/retrieveDpstamtDtbyDpstSttus | 예치금일자별예치현황조회 | DpstamtMngController | MyBatis | TB_DEPOSIT_DAILY_DEPOSIT_STATUS |
| /setlmng/dpstamtMng/retrieveDpstamtSttus | 예치금현황조회 | DpstamtMngController | MyBatis | TB_DEPOSIT_STATUS |
| /setlmng/dpstamtMng/registerDpstamtRefund | 예치금환불등록 | DpstamtMngController | MyBatis | TB_DEPOSIT_REFUND |
| /setlmng/easypaySetl/retrieveTvPayPaySttus | TV페이결제현황조회 | EasypaySetlController | MyBatis | TB_TVPAY_PAYMENT_STATUS |
| /setlmng/easypaySetl/retrieveTvPaySetlContraCmpr | TV페이정산대사비교조회 | EasypaySetlController | MyBatis | TB_TVPAY_SETTLEMENT_COMPARE |
| /setlmng/easypaySetl/retrieveTvPaySetlContra | TV페이정산대사조회 | EasypaySetlController | MyBatis | TB_TVPAY_SETTLEMENT |
| /setlmng/easypaySetl/retrieveNaverpayPaySttus | 네이버페이결제현황조회 | EasypaySetlController | MyBatis | TB_NAVERPAY_PAYMENT_STATUS |
| /setlmng/easypaySetl/retrieveNaverpaySetlContraCmpr | 네이버페이정산대사비교조회 | EasypaySetlController | MyBatis | TB_NAVERPAY_SETTLEMENT_COMPARE |
| /setlmng/easypaySetl/retrieveNaverpaySetlContra | 네이버페이정산대사조회 | EasypaySetlController | MyBatis | TB_NAVERPAY_SETTLEMENT |
| /setlmng/easypaySetl/retrieveSamsungpayPaySttus | 삼성페이결제현황조회 | EasypaySetlController | MyBatis | TB_SAMSUNGPAY_PAYMENT_STATUS |
| /setlmng/easypaySetl/retrieveSamsungpaySetlContra | 삼성페이정산대사조회 | EasypaySetlController | MyBatis | TB_SAMSUNGPAY_SETTLEMENT |
| /setlmng/easypaySetl/retrieveSamsungpaySetlCmprContra | 삼성페이정산비교대사조회 | EasypaySetlController | MyBatis | TB_SAMSUNGPAY_SETTLEMENT_COMPARE |
| /setlmng/easypaySetl/retrieveSettlebankPaySttus | 세틀뱅크결제현황조회 | EasypaySetlController | MyBatis | TB_SETTLEBANK_PAYMENT_STATUS |
| /setlmng/easypaySetl/retrieveSettlebankAdiSvcSttus | 세틀뱅크부가서비스현황조회 | EasypaySetlController | MyBatis | TB_SETTLEBANK_ADDITIONAL_SERVICE_STATUS |
| /setlmng/easypaySetl/retrieveSettlebankSetlContraCmpr | 세틀뱅크정산대사비교조회 | EasypaySetlController | MyBatis | TB_SETTLEBANK_SETTLEMENT_COMPARE |
| /setlmng/easypaySetl/retrieveSettlebankSetlContra | 세틀뱅크정산대사조회 | EasypaySetlController | MyBatis | TB_SETTLEBANK_SETTLEMENT |
| /setlmng/easypaySetl/retrieveSmilepayPaySttus | 스마일페이결제현황조회 | EasypaySetlController | MyBatis | TB_SMILEPAY_PAYMENT_STATUS |
| /setlmng/easypaySetl/retrieveSmilepaySetlContraCmpr | 스마일페이정산대사비교조회 | EasypaySetlController | MyBatis | TB_SMILEPAY_SETTLEMENT_COMPARE |
| /setlmng/easypaySetl/retrieveSmilepaySetlContra | 스마일페이정산대사조회 | EasypaySetlController | MyBatis | TB_SMILEPAY_SETTLEMENT |
| /setlmng/easypaySetl/retrieveOneClickSttus | 원클릭현황조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveChaiPaySttus | 차이결제현황조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveChaiSetlContra | 차이정산대사조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveChaiSetlCmprContra | 차이정산비교대사조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveKakaotalkpayPaySttus | 카카오톡페이결제현황조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveKakaotalkpaySetlContraCmpr | 카카오톡페이정산대사비교조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/easypaySetl/retrieveKakaotalkpaySetlContra | 카카오톡페이정산대사조회 | EasypaySetlController | MyBatis | 존재하지 않음 |
| /setlmng/monthlySettle/retrieveVenChargeRewardLst | 정산관리>협력사부담보상조회>협력사부담보상조회 | MonthlySettleController | MyBatis | TB_VENDOR_CHARGE_REWARD |
| /setlmng/monthlySettle/retrieveCgvIssueCjGcUseList | 정산관리>CGV발행CJ상품권사용내역>CJ상품권사용내역 | MonthlySettleController | MyBatis | TB_CGV_ISSUE_CJGC_USE |
| /setlmng/monthlySettle/retrieveCgvIssueCjGcUseUploadList | 정산관리>CGV발행CJ상품권사용내역>CGV회입내역 | MonthlySettleController | MyBatis | TB_OFFICIAL_PREDC_UPLOAD |
| /setlmng/monthlySettle/saveCgvIssueCjGcUseUpload | CGV회입내역저장 | MonthlySettleController | MyBatis | TB_CGV_ISSUE_CJGC_USE_UPLOAD |
| /setlmng/monthlySettle/retrieveOffclPredcUploadList | 정산관리>임직원선할인조회>임직원추출내역 | MonthlySettleController | MyBatis | TB_OFFICIAL_PREDC_UPLOAD |
| /setlmng/monthlySettle/saveOffclPredcUpload | 임직원추출내역저장 | MonthlySettleController | MyBatis | TB_OFFICIAL_PREDC_UPLOAD |
| /setlmng/monthlySettle/retrieveOffclPredcList | 정산관리>임직원선할인조회>임직원선할인내역 | MonthlySettleController | MyBatis | TB_OFFICIAL_PREDC |
| /setlmng/cpnRsltMng/retrieveOfferByCouponResult | 오퍼별 쿠폰실적 조회 | OfferByCouponResultExtractController | MyBatis | TB_OFFER_COUPON_RESULT |
| /setlmng/cpnRsltMng/retrieveOfferByCouponResultForExcel | 오퍼별 쿠폰실적 엑셀 다운로드 | OfferByCouponResultExtractController | MyBatis | 존재하지 않음 |
| /setlmng/saveamtMng/retrieveRwrdSaveamtClose | 정산관리>보상적립금월집계>보상적립금마감조회 | SaveamtMngController | MyBatis | TB_REWARD_SAVEAMT_CLOSE |
| /setlmng/saveamtMng/saveRwrdSaveamtClose | 정산관리>보상적립금월집계>보상적립금마감저장 | SaveamtMngController | MyBatis | TB_REWARD_SAVEAMT_CLOSE |
| /setlmng/saveamtMng/registerSaveamtTran | 고객>고객병합* CustomerInformationManagerImpl.java getSaveAmountManagementManager( ).requestSaveAmountCustomerTrans( saveAmountUseDto ); 참조 | SaveamtMngController | MyBatis | TB_SAVEAMT_TRANSFER |
| /setlmng/saveamtMng/retrieveSaveamtExtntPreplnAgg | 정산관리>고객별적립금관리>소멸예정금액조회팝업>적립금소멸예정집계조회 | SaveamtMngController | MyBatis | TB_SAVEAMT_EXTINCT_PREPLAN_AGGREGATE |
| /setlmng/saveamtMng/retrieveSaveamtExtntPreplnDtl | 정산관리>고객별적립금관리>소멸예정금액조회팝업>적립금소멸예정상세조회 | SaveamtMngController | MyBatis | TB_SAVEAMT_EXTINCT_PREPLAN_DETAIL |
| /setlmng/saveamtMng/retrieveHwrtGiveSaveamtMnbyDtl | 수기부여적립금월별상세조회 | SaveamtMngController | MyBatis | TB_MANUAL_GIVE_SAVEAMT_MONTHLY_DETAIL |
| /setlmng/saveamtMng/retrieveHwrtGiveSaveamtMnbyAgg | 정산관리>수기부여적립금월별집계>월집계 탭 | SaveamtMngController | MyBatis | TB_MANUAL_GIVE_SAVEAMT_MONTHLY_AGGREGATE |
| /setlmng/saveamtMng/registerSaveamtTranCncl | 고객>고객병합 취소시 사용 * StoreCustomerMergeCancelProcessActiongetSaveAmountManagementManager( ).requestSaveAmountCustomerTransUndo( saveAmountUseDto ); 참조 | SaveamtMngController | MyBatis | TB_SAVEAMT_TRANSFER |
| /setlmng/saveamtMng/retrieveCustbySaveamtDtl | 정산관리>고객별적립금관리>적립금상세내역탭 | SaveamtMngController | MyBatis | TB_CUSTOMER_SAVEAMT_DETAIL |
| /setlmng/saveamtMng/retrieveCustbySaveamtHist | 정산관리>고객별적립금관리>적립금이력 탭 | SaveamtMngController | MyBatis | TB_CUSTOMER_SAVEAMT_HISTORY |
| /setlmng/saveamtMng/registerSaveamtCmplt | 정산관리>고객별적립금관리>적립금부여완료등록 | SaveamtMngController | MyBatis | TB_SAVEAMT_COMPLETE |
| /setlmng/saveamtMng/saveHmspSaveamtHwrtGive | 정산관리>고객별적립금관리>홈쇼핑적립금수기부여저장 | SaveamtMngController | MyBatis | TB_HSAVEAMT_MANUAL_GIVE |
| /setlmng/saveamtMng/cancelSaveamtHwrtGive | 정산관리>고객별적립금관리>적립금수기부여취소 | SaveamtMngController | MyBatis | TB_SAVEAMT_MANUAL_GIVE |
| /setlmng/saveamtMng/retrieveOfferbySaveamtHist | 오퍼별적립금내역조회 | SaveamtMngController | MyBatis | TB_OFFER_SAVEAMT_HISTORY |
| /setlmng/saveamtMng/retrieveOfferbySaveamt | 오퍼별적립금내역 | SaveamtMngController | MyBatis | TB_OFFER_SAVEAMT_AGGREGATE |
| /setlmng/saveamtMng/retrieveOfferbySaveamtGive | 오퍼별적립금지급상세내역 | SaveamtMngController | MyBatis | TB_OFFER_SAVEAMT_GIVE |
| /setlmng/saveamtMng/retrieveOfferbySaveamtUse | 오퍼별적립금사용상세내역 | SaveamtMngController | MyBatis | TB_OFFER_SAVEAMT_USE |
| /setlmng/saveamtMng/retrieveSaveamtSetlExptRst | 정산관리>고객별적립금관리>정립금정산미리보기팝업 | SaveamtMngController | MyBatis | TB_SAVEAMT_SETTLEMENT_EXPECTED_RESULT |
| /setlmng/saveamtMng/registerSaveamtSetl | 정산관리>고객별적립금관리>정립금정산미리보기팝업 > 정산버튼 | SaveamtMngController | MyBatis | TB_SAVEAMT_SETTLEMENT |
| /setlmng/saveamtMng/retrievePnltyUnagreeLst | savePnltyUnagreeExcpRsn | SaveamtMngController | MyBatis | TB_PENALTY_UNAGREE |
| /setlmng/saveamtMng/savePnltyUnagreeExcpRsn | 패널티의의제기소명사유저장 | SaveamtMngController | MyBatis | TB_PENALTY_UNAGREE |
| /setlmng/saveamtMng/savePnltyUnagreeMdStnby | 패널티의의제기MD대기저장 | SaveamtMngController | MyBatis | TB_PENALTY_UNAGREE |
| /setlmng/saveamtMng/retrieveHmspSaveamtHwrtGiveSubtTgt | 정산관리>적립금수기부여차감대상 | SaveamtMngController | MyBatis | TB_HSAVEAMT_MANUAL_GIVE_SUBTRACT_TARGET |
| /setlmng/saveamtMng/retrieveSaveamtDtbyAggcht | 정산관리>적립금일자별집계표 | SaveamtMngController | MyBatis | TB_SAVEAMT_DAILY_AGGREGATE |
| /setlmng/saveamtMng/retrieveCustbySaveamtDtlPopup | 정산관리>고객별적립금관리>적립금세부 팝업 | SaveamtMngController | MyBatis | TB_CUSTOMER_SAVEAMT_DETAIL_POPUP |
| /setlmng/saveamtMng/cancelOfferHsaveamtGiveList | 오퍼 홈쇼핑적립금부여목록 취소 | SaveamtMngController | MyBatis | TB_OFFER_HSAVEAMT_GIVE |
| /setlmng/saveamtMng/bndeCancelOfferHsaveamtGiveList | 오퍼 홈쇼핑적립금부여목록 일괄취소 | SaveamtMngController | MyBatis | TB_OFFER_HSAVEAMT_GIVE_BUNDLE |
| /setlmng/saveamtMng/registerSaveamtUseCmplt | 정산관리>고객별적립금관리>적립금사용완료등록 | SaveamtMngController | MyBatis | TB_SAVEAMT_USE_COMPLETE |
| /setlmng/tvPoint/selectTvPointByOffer | 오퍼별 방송상품지원금 내역 조회 | TvPointMngController | MyBatis | TB_TV_POINT_BY_OFFER |
| /setlmng/tvPoint/selectTvPointDailySummary | 방송상품지원금 일자별 집계표 조회 | TvPointMngController | MyBatis | TB_TV_POINT_DAILY_SUMMARY |
| /setlmng/venSendMailMng/retrieveVenSendHistList | 협력사 이메일 발송 이력 조회 | VenSendMailMngController | MyBatis | TB_VENDOR_SEND_MAIL_HISTORY |
| /sub-payments/balance-onstyle | 기타결제수단별 잔액조회 | SubPaymentController | MyBatis | TB_ASSISTANT_PAYMENT_METHOD_REMAINDER, TB_ASSISTANT_PAYMENT_METHOD_REMAINDER_ONSTYLE |
| /tv-point/balance/{customerNo} | 방송상품지원금 잔액조회 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/balance-with-item/{customerNo} | 방송상품지원금 잔액조회 - 상품정보 사용가능여부 검증 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/history/detail/{customerNo} | 방송상품지원금 사용내역 조회 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/use/{preOrderNo}/{orderNo}/{customerNo} | 방송상품지원금 사용처리 - 온스타일용 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/use/{orderNo}/{customerNo} | 방송상품지원금 사용처리 - 온트러스트용 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/cancel/{orderNo}/{customerNo} | 방송상품지원금 취소처리 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/cancel/compensation | 방송상품지원금 취소처리 - 보상트렌젝션 배치용 | TvPointController | MyBatis | 존재하지 않음 |
| /tv-point/return/{orderNo}/{customerNo} | 방송상품지원금 반품처리 | TvPointController | MyBatis | 존재하지 않음 |