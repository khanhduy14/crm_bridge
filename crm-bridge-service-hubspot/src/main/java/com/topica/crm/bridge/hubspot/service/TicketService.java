package com.topica.crm.bridge.hubspot.service;

import com.github.icovn.http.client.HttpResult;
import com.github.icovn.util.ExceptionUtil;
import com.github.icovn.util.MapperUtil;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicket;
import com.topica.crm.bridge.hubspot.entity.ticket.HubspotTicketList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TicketService extends BaseService {

  public HubspotTicket getTicket(String id) {
    String uriTemplate =
        "/crm-objects/v1/objects/tickets/{ID}?hapikey={KEY}&includePropertyVersions=true&properties=closed_date&properties=created_by&properties=createdate&properties=first_agent_reply_date&properties=hs_conversations_originating_thread_id&properties=hs_custom_inbox&properties=hs_date_entered_1&properties=hs_date_entered_2&properties=hs_date_entered_216372&properties=hs_date_entered_216401&properties=hs_date_entered_216402&properties=hs_date_entered_216403&properties=hs_date_entered_216404&properties=hs_date_entered_216407&properties=hs_date_entered_293521&properties=hs_date_entered_293551&properties=hs_date_entered_293552&properties=hs_date_entered_293553&properties=hs_date_entered_293554&properties=hs_date_entered_3&properties=hs_date_entered_339465&properties=hs_date_entered_339466&properties=hs_date_entered_339468&properties=hs_date_entered_339505&properties=hs_date_entered_342365&properties=hs_date_entered_342413&properties=hs_date_entered_342479&properties=hs_date_entered_342611&properties=hs_date_entered_346463&properties=hs_date_entered_346474&properties=hs_date_entered_346475&properties=hs_date_entered_346536&properties=hs_date_entered_346576&properties=hs_date_entered_346584&properties=hs_date_entered_346621&properties=hs_date_entered_348169&properties=hs_date_entered_4&properties=hs_date_entered_416487&properties=hs_date_exited_1&properties=hs_date_exited_2&properties=hs_date_exited_216372&properties=hs_date_exited_216401&properties=hs_date_exited_216402&properties=hs_date_exited_216403&properties=hs_date_exited_216404&properties=hs_date_exited_216407&properties=hs_date_exited_293521&properties=hs_date_exited_293551&properties=hs_date_exited_293552&properties=hs_date_exited_293553&properties=hs_date_exited_293554&properties=hs_date_exited_3&properties=hs_date_exited_339465&properties=hs_date_exited_339466&properties=hs_date_exited_339468&properties=hs_date_exited_339505&properties=hs_date_exited_342365&properties=hs_date_exited_342413&properties=hs_date_exited_342479&properties=hs_date_exited_342611&properties=hs_date_exited_346463&properties=hs_date_exited_346474&properties=hs_date_exited_346475&properties=hs_date_exited_346536&properties=hs_date_exited_346576&properties=hs_date_exited_346584&properties=hs_date_exited_346621&properties=hs_date_exited_348169&properties=hs_date_exited_4&properties=hs_date_exited_416487&properties=hs_external_object_ids&properties=hs_feedback_last_ces_follow_up&properties=hs_feedback_last_ces_rating&properties=hs_feedback_last_survey_date&properties=hs_file_upload&properties=hs_last_email_activity&properties=hs_last_email_date&properties=hs_lastactivitydate&properties=hs_lastcontacted&properties=hs_lastmodifieddate&properties=hs_merged_object_ids&properties=hs_nextactivitydate&properties=hs_num_times_contacted&properties=hs_object_id&properties=hs_originating_email_engagement_id&properties=hs_pipeline&properties=hs_pipeline_stage&properties=hs_resolution&properties=hs_ticket_category&properties=hs_ticket_id&properties=hs_ticket_priority&properties=hs_time_in_1&properties=hs_time_in_2&properties=hs_time_in_216372&properties=hs_time_in_216401&properties=hs_time_in_216402&properties=hs_time_in_216403&properties=hs_time_in_216404&properties=hs_time_in_216407&properties=hs_time_in_293521&properties=hs_time_in_293551&properties=hs_time_in_293552&properties=hs_time_in_293553&properties=hs_time_in_293554&properties=hs_time_in_3&properties=hs_time_in_339465&properties=hs_time_in_339466&properties=hs_time_in_339468&properties=hs_time_in_339505&properties=hs_time_in_342365&properties=hs_time_in_342413&properties=hs_time_in_342479&properties=hs_time_in_342611&properties=hs_time_in_346463&properties=hs_time_in_346474&properties=hs_time_in_346475&properties=hs_time_in_346536&properties=hs_time_in_346576&properties=hs_time_in_346584&properties=hs_time_in_346621&properties=hs_time_in_348169&properties=hs_time_in_4&properties=hs_time_in_416487&properties=hubspot_owner_assigneddate&properties=last_engagement_date&properties=last_reply_date&properties=nps_follow_up_answer&properties=nps_follow_up_question_version&properties=nps_score&properties=source_thread_id&properties=time_to_close&properties=time_to_first_agent_reply&properties=account_student_ticket&properties=subject&properties=ticket_category&properties=booking_day_ticket&properties=content&properties=type&properties=booking_time_ticket&properties=source_type&properties=sale_takecare_ticket&properties=source_ref&properties=tso_category&properties=parent_name_ticket&properties=tags&properties=hs_sales_email_last_replied&properties=hubspot_owner_id&properties=kid_name&properties=notes_last_contacted&properties=notes_last_updated&properties=notes_next_activity_date&properties=num_contacted_notes&properties=num_notes&properties=class_code_ticket&properties=hubspot_team_id&properties=hs_all_owner_ids&properties=phone_number_ticket&properties=email_ticket&properties=hs_all_team_ids&properties=hs_all_accessible_team_ids&properties=reason_ticket&properties=score_link&properties=customer_problems&properties=report&properties=supporter&properties=test_technique_time";
    HttpResult httpResult = getObject(uriTemplate, id);

    if (httpResult.getStatusCode() == 200) {
      try {
        return MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotTicket.class);
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getDeal)httpResult: {}", httpResult);
      return null;
    }
  }

  public HubspotTicketList getTickets(long offset) {
    String uriTemplate =
        "/crm-objects/v1/objects/tickets/paged?hapikey={KEY}&vidOffset={OFFSET}&offset={OFFSET}&count=100";

    HttpResult httpResult = getListObject(uriTemplate, offset);

    if (httpResult.getStatusCode() == 200) {
      try {
        return MapperUtil.getMapper().readValue(httpResult.getBody(), HubspotTicketList.class);
      } catch (Exception ex) {
        log.error(ExceptionUtil.getFullStackTrace(ex, true));
        return null;
      }
    } else {
      log.error("(getDeals)httpResult: {}", httpResult);
      return null;
    }
  }
}
