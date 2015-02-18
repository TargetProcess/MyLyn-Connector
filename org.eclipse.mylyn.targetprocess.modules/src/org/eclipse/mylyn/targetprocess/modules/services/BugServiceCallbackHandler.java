
/**
 * BugServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  BugServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class BugServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public BugServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public BugServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for retrieveOpenForUser method
            * override this method for handling normal response from retrieveOpenForUser operation
            */
           public void receiveResultretrieveOpenForUser(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveOpenForUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForUser operation
           */
            public void receiveErrorretrieveOpenForUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRoleEffortsForBug method
            * override this method for handling normal response from retrieveRoleEffortsForBug operation
            */
           public void receiveResultretrieveRoleEffortsForBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveRoleEffortsForBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRoleEffortsForBug operation
           */
            public void receiveErrorretrieveRoleEffortsForBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeEffort method
            * override this method for handling normal response from changeEffort operation
            */
           public void receiveResultchangeEffort(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.ChangeEffortResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeEffort operation
           */
            public void receiveErrorchangeEffort(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delete method
            * override this method for handling normal response from delete operation
            */
           public void receiveResultdelete(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveOpenForMe method
            * override this method for handling normal response from retrieveOpenForMe operation
            */
           public void receiveResultretrieveOpenForMe(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveOpenForMeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForMe operation
           */
            public void receiveErrorretrieveOpenForMe(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieve method
            * override this method for handling normal response from retrieve operation
            */
           public void receiveResultretrieve(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeState method
            * override this method for handling normal response from changeState operation
            */
           public void receiveResultchangeState(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.ChangeStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeState operation
           */
            public void receiveErrorchangeState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForBug method
            * override this method for handling normal response from retrieveTeamsForBug operation
            */
           public void receiveResultretrieveTeamsForBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveTeamsForBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForBug operation
           */
            public void receiveErrorretrieveTeamsForBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUser method
            * override this method for handling normal response from assignUser operation
            */
           public void receiveResultassignUser(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AssignUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUser operation
           */
            public void receiveErrorassignUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRevisionAssignablesForBug method
            * override this method for handling normal response from retrieveRevisionAssignablesForBug operation
            */
           public void receiveResultretrieveRevisionAssignablesForBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveRevisionAssignablesForBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRevisionAssignablesForBug operation
           */
            public void receiveErrorretrieveRevisionAssignablesForBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForEntityState method
            * override this method for handling normal response from retrieveAllForEntityState operation
            */
           public void receiveResultretrieveAllForEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForEntityState operation
           */
            public void receiveErrorretrieveAllForEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForBuild method
            * override this method for handling normal response from retrieveAllForBuild operation
            */
           public void receiveResultretrieveAllForBuild(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForBuildResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForBuild operation
           */
            public void receiveErrorretrieveAllForBuild(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for create method
            * override this method for handling normal response from create operation
            */
           public void receiveResultcreate(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.CreateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from create operation
           */
            public void receiveErrorcreate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserByEmailOrLogin method
            * override this method for handling normal response from assignUserByEmailOrLogin operation
            */
           public void receiveResultassignUserByEmailOrLogin(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AssignUserByEmailOrLoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserByEmailOrLogin operation
           */
            public void receiveErrorassignUserByEmailOrLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForRelease method
            * override this method for handling normal response from retrieveAllForRelease operation
            */
           public void receiveResultretrieveAllForRelease(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForReleaseResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForRelease operation
           */
            public void receiveErrorretrieveAllForRelease(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAll method
            * override this method for handling normal response from retrieveAll operation
            */
           public void receiveResultretrieveAll(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForIteration method
            * override this method for handling normal response from retrieveAllForIteration operation
            */
           public void receiveResultretrieveAllForIteration(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForIterationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForIteration operation
           */
            public void receiveErrorretrieveAllForIteration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToBug method
            * override this method for handling normal response from addCommentToBug operation
            */
           public void receiveResultaddCommentToBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddCommentToBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToBug operation
           */
            public void receiveErroraddCommentToBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRevisionAssignableFromBug method
            * override this method for handling normal response from removeRevisionAssignableFromBug operation
            */
           public void receiveResultremoveRevisionAssignableFromBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RemoveRevisionAssignableFromBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRevisionAssignableFromBug operation
           */
            public void receiveErrorremoveRevisionAssignableFromBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForSeverity method
            * override this method for handling normal response from retrieveAllForSeverity operation
            */
           public void receiveResultretrieveAllForSeverity(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForSeverityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForSeverity operation
           */
            public void receiveErrorretrieveAllForSeverity(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromBug method
            * override this method for handling normal response from removeAttachmentFromBug operation
            */
           public void receiveResultremoveAttachmentFromBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RemoveAttachmentFromBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromBug operation
           */
            public void receiveErrorremoveAttachmentFromBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTeamToBug method
            * override this method for handling normal response from addTeamToBug operation
            */
           public void receiveResultaddTeamToBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddTeamToBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTeamToBug operation
           */
            public void receiveErroraddTeamToBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRevisionAssignableToBug method
            * override this method for handling normal response from addRevisionAssignableToBug operation
            */
           public void receiveResultaddRevisionAssignableToBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddRevisionAssignableToBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRevisionAssignableToBug operation
           */
            public void receiveErroraddRevisionAssignableToBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToBug method
            * override this method for handling normal response from addAttachmentToBug operation
            */
           public void receiveResultaddAttachmentToBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddAttachmentToBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToBug operation
           */
            public void receiveErroraddAttachmentToBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTeamFromBug method
            * override this method for handling normal response from removeTeamFromBug operation
            */
           public void receiveResultremoveTeamFromBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RemoveTeamFromBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTeamFromBug operation
           */
            public void receiveErrorremoveTeamFromBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updatePartial method
            * override this method for handling normal response from updatePartial operation
            */
           public void receiveResultupdatePartial(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.UpdatePartialResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updatePartial operation
           */
            public void receiveErrorupdatePartial(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPriorities method
            * override this method for handling normal response from getPriorities operation
            */
           public void receiveResultgetPriorities(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetPrioritiesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPriorities operation
           */
            public void receiveErrorgetPriorities(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSeverities method
            * override this method for handling normal response from getSeverities operation
            */
           public void receiveResultgetSeverities(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetSeveritiesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSeverities operation
           */
            public void receiveErrorgetSeverities(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForBug method
            * override this method for handling normal response from retrieveAttachmentsForBug operation
            */
           public void receiveResultretrieveAttachmentsForBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAttachmentsForBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForBug operation
           */
            public void receiveErrorretrieveAttachmentsForBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addBugWithAttachment method
            * override this method for handling normal response from addBugWithAttachment operation
            */
           public void receiveResultaddBugWithAttachment(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AddBugWithAttachmentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addBugWithAttachment operation
           */
            public void receiveErroraddBugWithAttachment(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForUserStory method
            * override this method for handling normal response from retrieveAllForUserStory operation
            */
           public void receiveResultretrieveAllForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForUserStory operation
           */
            public void receiveErrorretrieveAllForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForLastCommentUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForLastCommentUser operation
           */
            public void receiveErrorretrieveAllForLastCommentUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForBug method
            * override this method for handling normal response from retrieveCommentsForBug operation
            */
           public void receiveResultretrieveCommentsForBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCommentsForBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForBug operation
           */
            public void receiveErrorretrieveCommentsForBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getByID method
            * override this method for handling normal response from getByID operation
            */
           public void receiveResultgetByID(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.GetByIDResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getByID operation
           */
            public void receiveErrorgetByID(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserAsRole method
            * override this method for handling normal response from assignUserAsRole operation
            */
           public void receiveResultassignUserAsRole(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AssignUserAsRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserAsRole operation
           */
            public void receiveErrorassignUserAsRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUserByEmailOrLoginAsRole method
            * override this method for handling normal response from assignUserByEmailOrLoginAsRole operation
            */
           public void receiveResultassignUserByEmailOrLoginAsRole(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.AssignUserByEmailOrLoginAsRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUserByEmailOrLoginAsRole operation
           */
            public void receiveErrorassignUserByEmailOrLoginAsRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForPriority method
            * override this method for handling normal response from retrieveAllForPriority operation
            */
           public void receiveResultretrieveAllForPriority(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveAllForPriorityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForPriority operation
           */
            public void receiveErrorretrieveAllForPriority(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromBug method
            * override this method for handling normal response from removeCommentFromBug operation
            */
           public void receiveResultremoveCommentFromBug(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RemoveCommentFromBugResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromBug operation
           */
            public void receiveErrorremoveCommentFromBug(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.BugServiceStub.RetrieveCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCount operation
           */
            public void receiveErrorretrieveCount(java.lang.Exception e) {
            }
                


    }
    