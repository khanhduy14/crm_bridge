package com.topica.crm.bridge.odoo.entity.sale;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OdooSale {
  private List<Object> teamId;
  private List<Object> analyticAccountId;
  private List<Object> pricelistId;
  private List<Object> createUid;
  private List<Object> userId;
  private List<Object> partnerInvoiceId;
  private List<Object> partnerShippingId;
  private List<Object> partnerId;
  private List<Object> companyId;
  private List<Object> procurementGroupId;
  private List<Object> currencyId;
  private List<Object> warehouseId;

  private String writeDate;
  private Integer timesheetCount;
  private String typeName;
  private List<Integer> messagePartnerIds = new LinkedList<>();
  private List<Object> invoiceIds = new LinkedList<>();
  private String validityDate;
  private Integer messageMainAttachmentId;
  private Integer amountTotal;
  private String confirmationDate;
  private String activityDateDeadline;
  private List<Object> messageChannelIds = new LinkedList<>();
  private Integer paymentTermId;
  private Integer activityState;
  private Integer reference;
  private Integer messageHasErrorCounter;
  private List<Object> activityIds = new LinkedList<>();
  private String dateOrder;
  private String effectiveDate;
  private List<Object> amountByGroup = new LinkedList<>();
  private Integer id;
  private String state;
  private List<Object> transactionIds = new LinkedList<>();
  private String createDate;
  private Integer mediumId;
  private String pickingPolicy;
  private Integer campaignId;
  private Integer tasksCount;
  private List<Object> timesheetIds = new LinkedList<>();
  private List<Object> tagIds = new LinkedList<>();
  private Integer messageAttachmentCount;
  private String lastUpdate;
  private Integer subscriptionCount;
  private String clientOrderRef;
  private List<Object> pickingIds = new LinkedList<>();
  private Integer activityUserId;
  private List<Integer> messageFollowerIds = new LinkedList<>();
  private Integer saleOrderTemplateId;
  private String displayName;
  private Integer accessToken;
  private String accessWarning;
  private Integer opportunityId;
  private String expectedDate;
  private Integer incoterm;
  private String name;
  private List<Object> websiteMessageIds = new LinkedList<>();
  private String subscriptionManagement;
  private String invoiceStatus;
  private String note;
  private List<Object> writeUid;
  private Boolean messageIsFollower;
  private Boolean requirePayment;
  private Integer signature;
  private Integer origin;
  private Integer fiscalPositionId;
  private Integer deliveryCount;
  private Integer messageUnread;
  private String commitmentDate;
  private Integer currencyRate;
  private Integer amountTax;
  private Integer remainingValidityDays;
  private Integer amountUntaxed;
  private List<Object> orderLine = new LinkedList<>();
  private Integer amountUndiscounted;
  private List<Object> saleOrderOptionIds = new LinkedList<>();
  private List<Object> tasksIds = new LinkedList<>();
  private Boolean requireSignature;
  private String accessUrl;
  private Integer invoiceCount;
  private List<Object> projectIds = new LinkedList<>();
  private Integer messageUnreadCounter;
  private Integer activityTypeId;
  private Integer messageHasError;
  private Integer isExpired;
  private Integer signedBy;
  private Integer messageNeedaction;
  private Integer activitySummary;
  private Integer messageNeedactionCounter;
  private List<Object> authorizedTransactionIds = new LinkedList<>();
  private List<Integer> messageIds = new LinkedList<>();
  private Integer sourceId;
}
