
/**
 * RequestServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  RequestServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class RequestServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public RequestServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public RequestServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getByID method
            * override this method for handling normal response from getByID operation
            */
           public void receiveResultgetByID(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetByIDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getByID operation
           */
            public void receiveErrorgetByID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updatePartial method
            * override this method for handling normal response from updatePartial operation
            */
           public void receiveResultupdatePartial(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.UpdatePartialResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updatePartial operation
           */
            public void receiveErrorupdatePartial(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveOpenForMe method
            * override this method for handling normal response from retrieveOpenForMe operation
            */
           public void receiveResultretrieveOpenForMe(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveOpenForMeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForMe operation
           */
            public void receiveErrorretrieveOpenForMe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAll method
            * override this method for handling normal response from retrieveAll operation
            */
           public void receiveResultretrieveAll(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRevisionAssignableFromRequest method
            * override this method for handling normal response from removeRevisionAssignableFromRequest operation
            */
           public void receiveResultremoveRevisionAssignableFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveRevisionAssignableFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRevisionAssignableFromRequest operation
           */
            public void receiveErrorremoveRevisionAssignableFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForRequest method
            * override this method for handling normal response from retrieveAttachmentsForRequest operation
            */
           public void receiveResultretrieveAttachmentsForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachmentsForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForRequest operation
           */
            public void receiveErrorretrieveAttachmentsForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRequestersCount method
            * override this method for handling normal response from getRequestersCount operation
            */
           public void receiveResultgetRequestersCount(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetRequestersCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRequestersCount operation
           */
            public void receiveErrorgetRequestersCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRequestersForRequest method
            * override this method for handling normal response from retrieveRequestersForRequest operation
            */
           public void receiveResultretrieveRequestersForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveRequestersForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRequestersForRequest operation
           */
            public void receiveErrorretrieveRequestersForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isRequesterAttached method
            * override this method for handling normal response from isRequesterAttached operation
            */
           public void receiveResultisRequesterAttached(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.IsRequesterAttachedResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isRequesterAttached operation
           */
            public void receiveErrorisRequesterAttached(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createWithAutoAddingRequester method
            * override this method for handling normal response from createWithAutoAddingRequester operation
            */
           public void receiveResultcreateWithAutoAddingRequester(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.CreateWithAutoAddingRequesterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createWithAutoAddingRequester operation
           */
            public void receiveErrorcreateWithAutoAddingRequester(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveProducts method
            * override this method for handling normal response from retrieveProducts operation
            */
           public void receiveResultretrieveProducts(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveProductsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveProducts operation
           */
            public void receiveErrorretrieveProducts(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToRequest method
            * override this method for handling normal response from addCommentToRequest operation
            */
           public void receiveResultaddCommentToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddCommentToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToRequest operation
           */
            public void receiveErroraddCommentToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveOpenForUser method
            * override this method for handling normal response from retrieveOpenForUser operation
            */
           public void receiveResultretrieveOpenForUser(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveOpenForUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForUser operation
           */
            public void receiveErrorretrieveOpenForUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRequestGeneralToRequest method
            * override this method for handling normal response from addRequestGeneralToRequest operation
            */
           public void receiveResultaddRequestGeneralToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddRequestGeneralToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRequestGeneralToRequest operation
           */
            public void receiveErroraddRequestGeneralToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for save method
            * override this method for handling normal response from save operation
            */
           public void receiveResultsave(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.SaveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from save operation
           */
            public void receiveErrorsave(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserByEmailOrLoginAsRole method
            * override this method for handling normal response from assignUserByEmailOrLoginAsRole operation
            */
           public void receiveResultassignUserByEmailOrLoginAsRole(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AssignUserByEmailOrLoginAsRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserByEmailOrLoginAsRole operation
           */
            public void receiveErrorassignUserByEmailOrLoginAsRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTeamToRequest method
            * override this method for handling normal response from addTeamToRequest operation
            */
           public void receiveResultaddTeamToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddTeamToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTeamToRequest operation
           */
            public void receiveErroraddTeamToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserByEmailOrLogin method
            * override this method for handling normal response from assignUserByEmailOrLogin operation
            */
           public void receiveResultassignUserByEmailOrLogin(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AssignUserByEmailOrLoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserByEmailOrLogin operation
           */
            public void receiveErrorassignUserByEmailOrLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCount operation
           */
            public void receiveErrorretrieveCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForEntityState method
            * override this method for handling normal response from retrieveAllForEntityState operation
            */
           public void receiveResultretrieveAllForEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForEntityState operation
           */
            public void receiveErrorretrieveAllForEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delete method
            * override this method for handling normal response from delete operation
            */
           public void receiveResultdelete(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRequestRequesterToRequest method
            * override this method for handling normal response from addRequestRequesterToRequest operation
            */
           public void receiveResultaddRequestRequesterToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddRequestRequesterToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRequestRequesterToRequest operation
           */
            public void receiveErroraddRequestRequesterToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRequester method
            * override this method for handling normal response from removeRequester operation
            */
           public void receiveResultremoveRequester(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveRequesterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRequester operation
           */
            public void receiveErrorremoveRequester(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForLastCommentUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForLastCommentUser operation
           */
            public void receiveErrorretrieveAllForLastCommentUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for create method
            * override this method for handling normal response from create operation
            */
           public void receiveResultcreate(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.CreateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from create operation
           */
            public void receiveErrorcreate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUser method
            * override this method for handling normal response from assignUser operation
            */
           public void receiveResultassignUser(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AssignUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUser operation
           */
            public void receiveErrorassignUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToRequest method
            * override this method for handling normal response from addAttachmentToRequest operation
            */
           public void receiveResultaddAttachmentToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddAttachmentToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToRequest operation
           */
            public void receiveErroraddAttachmentToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRevisionAssignableToRequest method
            * override this method for handling normal response from addRevisionAssignableToRequest operation
            */
           public void receiveResultaddRevisionAssignableToRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AddRevisionAssignableToRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRevisionAssignableToRequest operation
           */
            public void receiveErroraddRevisionAssignableToRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromRequest method
            * override this method for handling normal response from removeAttachmentFromRequest operation
            */
           public void receiveResultremoveAttachmentFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveAttachmentFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromRequest operation
           */
            public void receiveErrorremoveAttachmentFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieve method
            * override this method for handling normal response from retrieve operation
            */
           public void receiveResultretrieve(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRevisionAssignablesForRequest method
            * override this method for handling normal response from retrieveRevisionAssignablesForRequest operation
            */
           public void receiveResultretrieveRevisionAssignablesForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveRevisionAssignablesForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRevisionAssignablesForRequest operation
           */
            public void receiveErrorretrieveRevisionAssignablesForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForRequests method
            * override this method for handling normal response from retrieveTeamsForRequests operation
            */
           public void receiveResultretrieveTeamsForRequests(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveTeamsForRequestsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForRequests operation
           */
            public void receiveErrorretrieveTeamsForRequests(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForIteration method
            * override this method for handling normal response from retrieveAllForIteration operation
            */
           public void receiveResultretrieveAllForIteration(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForIterationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForIteration operation
           */
            public void receiveErrorretrieveAllForIteration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeEffort method
            * override this method for handling normal response from changeEffort operation
            */
           public void receiveResultchangeEffort(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.ChangeEffortResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeEffort operation
           */
            public void receiveErrorchangeEffort(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForPriority method
            * override this method for handling normal response from retrieveAllForPriority operation
            */
           public void receiveResultretrieveAllForPriority(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForPriorityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForPriority operation
           */
            public void receiveErrorretrieveAllForPriority(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRoleEffortsForRequest method
            * override this method for handling normal response from retrieveRoleEffortsForRequest operation
            */
           public void receiveResultretrieveRoleEffortsForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveRoleEffortsForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRoleEffortsForRequest operation
           */
            public void receiveErrorretrieveRoleEffortsForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPriorities method
            * override this method for handling normal response from getPriorities operation
            */
           public void receiveResultgetPriorities(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetPrioritiesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPriorities operation
           */
            public void receiveErrorgetPriorities(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeState method
            * override this method for handling normal response from changeState operation
            */
           public void receiveResultchangeState(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.ChangeStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeState operation
           */
            public void receiveErrorchangeState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForRelease method
            * override this method for handling normal response from retrieveAllForRelease operation
            */
           public void receiveResultretrieveAllForRelease(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForReleaseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForRelease operation
           */
            public void receiveErrorretrieveAllForRelease(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRequestersCountArray method
            * override this method for handling normal response from getRequestersCountArray operation
            */
           public void receiveResultgetRequestersCountArray(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetRequestersCountArrayResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRequestersCountArray operation
           */
            public void receiveErrorgetRequestersCountArray(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachedRequestsForRequest method
            * override this method for handling normal response from retrieveAttachedRequestsForRequest operation
            */
           public void receiveResultretrieveAttachedRequestsForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAttachedRequestsForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachedRequestsForRequest operation
           */
            public void receiveErrorretrieveAttachedRequestsForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTeamFromRequest method
            * override this method for handling normal response from removeTeamFromRequest operation
            */
           public void receiveResultremoveTeamFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveTeamFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTeamFromRequest operation
           */
            public void receiveErrorremoveTeamFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRequestRequesterFromRequest method
            * override this method for handling normal response from removeRequestRequesterFromRequest operation
            */
           public void receiveResultremoveRequestRequesterFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveRequestRequesterFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRequestRequesterFromRequest operation
           */
            public void receiveErrorremoveRequestRequesterFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRequestTypes method
            * override this method for handling normal response from retrieveRequestTypes operation
            */
           public void receiveResultretrieveRequestTypes(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveRequestTypesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRequestTypes operation
           */
            public void receiveErrorretrieveRequestTypes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRequestGeneralFromRequest method
            * override this method for handling normal response from removeRequestGeneralFromRequest operation
            */
           public void receiveResultremoveRequestGeneralFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveRequestGeneralFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRequestGeneralFromRequest operation
           */
            public void receiveErrorremoveRequestGeneralFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromRequest method
            * override this method for handling normal response from removeCommentFromRequest operation
            */
           public void receiveResultremoveCommentFromRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RemoveCommentFromRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromRequest operation
           */
            public void receiveErrorremoveCommentFromRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForRequestType method
            * override this method for handling normal response from retrieveAllForRequestType operation
            */
           public void receiveResultretrieveAllForRequestType(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveAllForRequestTypeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForRequestType operation
           */
            public void receiveErrorretrieveAllForRequestType(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForRequest method
            * override this method for handling normal response from retrieveCommentsForRequest operation
            */
           public void receiveResultretrieveCommentsForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveCommentsForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForRequest operation
           */
            public void receiveErrorretrieveCommentsForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForRequest method
            * override this method for handling normal response from retrieveTeamsForRequest operation
            */
           public void receiveResultretrieveTeamsForRequest(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveTeamsForRequestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForRequest operation
           */
            public void receiveErrorretrieveTeamsForRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRelatedEntities method
            * override this method for handling normal response from retrieveRelatedEntities operation
            */
           public void receiveResultretrieveRelatedEntities(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.RetrieveRelatedEntitiesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRelatedEntities operation
           */
            public void receiveErrorretrieveRelatedEntities(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserAsRole method
            * override this method for handling normal response from assignUserAsRole operation
            */
           public void receiveResultassignUserAsRole(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.AssignUserAsRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserAsRole operation
           */
            public void receiveErrorassignUserAsRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.RequestServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                


    }
    