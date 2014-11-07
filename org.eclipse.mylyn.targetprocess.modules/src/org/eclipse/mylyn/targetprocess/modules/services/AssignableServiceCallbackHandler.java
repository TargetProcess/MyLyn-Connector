
/**
 * AssignableServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  AssignableServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AssignableServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AssignableServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AssignableServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.UpdatePartialResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updatePartial operation
           */
            public void receiveErrorupdatePartial(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAll method
            * override this method for handling normal response from retrieveAll operation
            */
           public void receiveResultretrieveAll(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTeamFromAssignable method
            * override this method for handling normal response from removeTeamFromAssignable operation
            */
           public void receiveResultremoveTeamFromAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RemoveTeamFromAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTeamFromAssignable operation
           */
            public void receiveErrorremoveTeamFromAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRoleEffortsForAssignable method
            * override this method for handling normal response from retrieveRoleEffortsForAssignable operation
            */
           public void receiveResultretrieveRoleEffortsForAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveRoleEffortsForAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRoleEffortsForAssignable operation
           */
            public void receiveErrorretrieveRoleEffortsForAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForAssignable method
            * override this method for handling normal response from retrieveTeamsForAssignable operation
            */
           public void receiveResultretrieveTeamsForAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveTeamsForAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForAssignable operation
           */
            public void receiveErrorretrieveTeamsForAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveCountResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForEntityStateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToAssignable method
            * override this method for handling normal response from addAttachmentToAssignable operation
            */
           public void receiveResultaddAttachmentToAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.AddAttachmentToAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToAssignable operation
           */
            public void receiveErroraddAttachmentToAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToAssignable method
            * override this method for handling normal response from addCommentToAssignable operation
            */
           public void receiveResultaddCommentToAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.AddCommentToAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToAssignable operation
           */
            public void receiveErroraddCommentToAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRevisionAssignableToAssignable method
            * override this method for handling normal response from addRevisionAssignableToAssignable operation
            */
           public void receiveResultaddRevisionAssignableToAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.AddRevisionAssignableToAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRevisionAssignableToAssignable operation
           */
            public void receiveErroraddRevisionAssignableToAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForLastCommentUserResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.CreateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from create operation
           */
            public void receiveErrorcreate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRevisionAssignableFromAssignable method
            * override this method for handling normal response from removeRevisionAssignableFromAssignable operation
            */
           public void receiveResultremoveRevisionAssignableFromAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RemoveRevisionAssignableFromAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRevisionAssignableFromAssignable operation
           */
            public void receiveErrorremoveRevisionAssignableFromAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieve method
            * override this method for handling normal response from retrieve operation
            */
           public void receiveResultretrieve(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTeamToAssignable method
            * override this method for handling normal response from addTeamToAssignable operation
            */
           public void receiveResultaddTeamToAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.AddTeamToAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTeamToAssignable operation
           */
            public void receiveErroraddTeamToAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForAssignable method
            * override this method for handling normal response from retrieveCommentsForAssignable operation
            */
           public void receiveResultretrieveCommentsForAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveCommentsForAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForAssignable operation
           */
            public void receiveErrorretrieveCommentsForAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeEffort method
            * override this method for handling normal response from changeEffort operation
            */
           public void receiveResultchangeEffort(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.ChangeEffortResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForPriorityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForPriority operation
           */
            public void receiveErrorretrieveAllForPriority(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForIteration method
            * override this method for handling normal response from retrieveAllForIteration operation
            */
           public void receiveResultretrieveAllForIteration(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForIterationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForIteration operation
           */
            public void receiveErrorretrieveAllForIteration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForRelease method
            * override this method for handling normal response from retrieveAllForRelease operation
            */
           public void receiveResultretrieveAllForRelease(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForReleaseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForRelease operation
           */
            public void receiveErrorretrieveAllForRelease(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromAssignable method
            * override this method for handling normal response from removeCommentFromAssignable operation
            */
           public void receiveResultremoveCommentFromAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RemoveCommentFromAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromAssignable operation
           */
            public void receiveErrorremoveCommentFromAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromAssignable method
            * override this method for handling normal response from removeAttachmentFromAssignable operation
            */
           public void receiveResultremoveAttachmentFromAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RemoveAttachmentFromAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromAssignable operation
           */
            public void receiveErrorremoveAttachmentFromAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRevisionAssignablesForAssignable method
            * override this method for handling normal response from retrieveRevisionAssignablesForAssignable operation
            */
           public void receiveResultretrieveRevisionAssignablesForAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveRevisionAssignablesForAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRevisionAssignablesForAssignable operation
           */
            public void receiveErrorretrieveRevisionAssignablesForAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForAssignable method
            * override this method for handling normal response from retrieveAttachmentsForAssignable operation
            */
           public void receiveResultretrieveAttachmentsForAssignable(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.RetrieveAttachmentsForAssignableResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForAssignable operation
           */
            public void receiveErrorretrieveAttachmentsForAssignable(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.AssignableServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                


    }
    