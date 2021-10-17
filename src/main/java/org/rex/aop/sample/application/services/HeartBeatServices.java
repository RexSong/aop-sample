package org.rex.aop.sample.application.services;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ComponentsApi;
import org.openapitools.client.model.PostPagesPageIdComponentsComponent;
import org.openapitools.client.model.PostPagesPageIdIncidents;
import org.openapitools.client.model.PostPagesPageIdIncidentsIncident;
import org.openapitools.client.model.PutPagesPageIdComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.openapitools.client.api.IncidentsApi;

import java.util.Arrays;

@Component
public class HeartBeatServices {

  private IncidentsApi incidentsApi;
  private ComponentsApi componentsApi;

  @Autowired
  public HeartBeatServices(IncidentsApi incidentsApi, ComponentsApi componentsApi) {
    this.incidentsApi = incidentsApi;
    this.componentsApi = componentsApi;
  }

  public void createIncident(String incidentName, StackTraceElement[] stackTraceElements, String pageId, String[] componentIds) throws ApiException {
    PostPagesPageIdIncidentsIncident postPagesPageIdIncidentsIncident = new PostPagesPageIdIncidentsIncident();
    postPagesPageIdIncidentsIncident.setName(incidentName);
    postPagesPageIdIncidentsIncident.setStatus(PostPagesPageIdIncidentsIncident.StatusEnum.INVESTIGATING);
    StringBuilder stringBuilder = new StringBuilder();
    Arrays.stream(stackTraceElements).forEach(s -> {
      stringBuilder.append(s);
      stringBuilder.append("\r\n");
    });
    postPagesPageIdIncidentsIncident.setBody(stringBuilder.toString());
    Arrays.stream(componentIds).forEach(c -> {
      postPagesPageIdIncidentsIncident.putComponentsItem(c, org.openapitools.client.model.Component.StatusEnum.MAJOR_OUTAGE.getValue());
      postPagesPageIdIncidentsIncident.addComponentIdsItem(c);
    });
    PostPagesPageIdIncidents postPagesPageIdIncidents = new PostPagesPageIdIncidents();
    postPagesPageIdIncidents.setIncident(postPagesPageIdIncidentsIncident);
    incidentsApi.postPagesPageIdIncidents(pageId, postPagesPageIdIncidents);
  }

  public void updateApiComponentToOperational(String pageId, String componentId) throws ApiException {
    org.openapitools.client.model.Component component = componentsApi.getPagesPageIdComponentsComponentId(pageId, componentId);
    PutPagesPageIdComponents putPagesPageIdComponents = new PutPagesPageIdComponents();
    PostPagesPageIdComponentsComponent postPagesPageIdComponentsComponent = new PostPagesPageIdComponentsComponent();
    postPagesPageIdComponentsComponent.setName(component.getName());
    postPagesPageIdComponentsComponent.setDescription(component.getDescription());
    postPagesPageIdComponentsComponent.setGroupId(component.getGroupId());
    postPagesPageIdComponentsComponent.setShowcase(component.getShowcase());
    postPagesPageIdComponentsComponent.setOnlyShowIfDegraded(component.getOnlyShowIfDegraded());
    postPagesPageIdComponentsComponent.setStartDate(component.getStartDate());
    postPagesPageIdComponentsComponent.setStatus(PostPagesPageIdComponentsComponent.StatusEnum.OPERATIONAL);
    putPagesPageIdComponents.setComponent(postPagesPageIdComponentsComponent);
    componentsApi.putPagesPageIdComponentsComponentId(pageId, componentId, putPagesPageIdComponents);
  }
}
