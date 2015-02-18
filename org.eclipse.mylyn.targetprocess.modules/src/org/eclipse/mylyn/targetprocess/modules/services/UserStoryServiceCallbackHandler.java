
/**
 * UserStoryServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  UserStoryServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class UserStoryServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public UserStoryServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public UserStoryServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveOpenForUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForUser operation
           */
            public void receiveErrorretrieveOpenForUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTaskToUserStory method
            * override this method for handling normal response from addTaskToUserStory operation
            */
           public void receiveResultaddTaskToUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddTaskToUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTaskToUserStory operation
           */
            public void receiveErroraddTaskToUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForFeature method
            * override this method for handling normal response from retrieveAllForFeature operation
            */
           public void receiveResultretrieveAllForFeature(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForFeatureResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForFeature operation
           */
            public void receiveErrorretrieveAllForFeature(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForUserStory method
            * override this method for handling normal response from retrieveCommentsForUserStory operation
            */
           public void receiveResultretrieveCommentsForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCommentsForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForUserStory operation
           */
            public void receiveErrorretrieveCommentsForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTeamToUserStory method
            * override this method for handling normal response from addTeamToUserStory operation
            */
           public void receiveResultaddTeamToUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddTeamToUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTeamToUserStory operation
           */
            public void receiveErroraddTeamToUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeEffort method
            * override this method for handling normal response from changeEffort operation
            */
           public void receiveResultchangeEffort(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.ChangeEffortResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForUserStory method
            * override this method for handling normal response from retrieveTeamsForUserStory operation
            */
           public void receiveResultretrieveTeamsForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveTeamsForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForUserStory operation
           */
            public void receiveErrorretrieveTeamsForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveOpenForMe method
            * override this method for handling normal response from retrieveOpenForMe operation
            */
           public void receiveResultretrieveOpenForMe(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveOpenForMeResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.ChangeStateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignUser method
            * override this method for handling normal response from assignUser operation
            */
           public void receiveResultassignUser(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AssignUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUser operation
           */
            public void receiveErrorassignUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromUserStory method
            * override this method for handling normal response from removeAttachmentFromUserStory operation
            */
           public void receiveResultremoveAttachmentFromUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RemoveAttachmentFromUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromUserStory operation
           */
            public void receiveErrorremoveAttachmentFromUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForEntityState method
            * override this method for handling normal response from retrieveAllForEntityState operation
            */
           public void receiveResultretrieveAllForEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForEntityState operation
           */
            public void receiveErrorretrieveAllForEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for create method
            * override this method for handling normal response from create operation
            */
           public void receiveResultcreate(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.CreateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AssignUserByEmailOrLoginResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForReleaseResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForIterationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForIteration operation
           */
            public void receiveErrorretrieveAllForIteration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToUserStory method
            * override this method for handling normal response from addCommentToUserStory operation
            */
           public void receiveResultaddCommentToUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddCommentToUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToUserStory operation
           */
            public void receiveErroraddCommentToUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTaskFromUserStory method
            * override this method for handling normal response from removeTaskFromUserStory operation
            */
           public void receiveResultremoveTaskFromUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RemoveTaskFromUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTaskFromUserStory operation
           */
            public void receiveErrorremoveTaskFromUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRoleEffortsForUserStory method
            * override this method for handling normal response from retrieveRoleEffortsForUserStory operation
            */
           public void receiveResultretrieveRoleEffortsForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveRoleEffortsForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRoleEffortsForUserStory operation
           */
            public void receiveErrorretrieveRoleEffortsForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetIDsResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.UpdatePartialResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetPrioritiesResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRevisionAssignableFromUserStory method
            * override this method for handling normal response from removeRevisionAssignableFromUserStory operation
            */
           public void receiveResultremoveRevisionAssignableFromUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RemoveRevisionAssignableFromUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRevisionAssignableFromUserStory operation
           */
            public void receiveErrorremoveRevisionAssignableFromUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRevisionAssignableToUserStory method
            * override this method for handling normal response from addRevisionAssignableToUserStory operation
            */
           public void receiveResultaddRevisionAssignableToUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddRevisionAssignableToUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRevisionAssignableToUserStory operation
           */
            public void receiveErroraddRevisionAssignableToUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToUserStory method
            * override this method for handling normal response from addAttachmentToUserStory operation
            */
           public void receiveResultaddAttachmentToUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AddAttachmentToUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToUserStory operation
           */
            public void receiveErroraddAttachmentToUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTeamFromUserStory method
            * override this method for handling normal response from removeTeamFromUserStory operation
            */
           public void receiveResultremoveTeamFromUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RemoveTeamFromUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTeamFromUserStory operation
           */
            public void receiveErrorremoveTeamFromUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRevisionAssignablesForUserStory method
            * override this method for handling normal response from retrieveRevisionAssignablesForUserStory operation
            */
           public void receiveResultretrieveRevisionAssignablesForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveRevisionAssignablesForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRevisionAssignablesForUserStory operation
           */
            public void receiveErrorretrieveRevisionAssignablesForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTasksForUserStory method
            * override this method for handling normal response from retrieveTasksForUserStory operation
            */
           public void receiveResultretrieveTasksForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveTasksForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTasksForUserStory operation
           */
            public void receiveErrorretrieveTasksForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveNames method
            * override this method for handling normal response from retrieveNames operation
            */
           public void receiveResultretrieveNames(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveNamesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveNames operation
           */
            public void receiveErrorretrieveNames(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromUserStory method
            * override this method for handling normal response from removeCommentFromUserStory operation
            */
           public void receiveResultremoveCommentFromUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RemoveCommentFromUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromUserStory operation
           */
            public void receiveErrorremoveCommentFromUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForLastCommentUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForLastCommentUser operation
           */
            public void receiveErrorretrieveAllForLastCommentUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForUserStory method
            * override this method for handling normal response from retrieveAttachmentsForUserStory operation
            */
           public void receiveResultretrieveAttachmentsForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAttachmentsForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForUserStory operation
           */
            public void receiveErrorretrieveAttachmentsForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getByID method
            * override this method for handling normal response from getByID operation
            */
           public void receiveResultgetByID(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AssignUserAsRoleResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.AssignUserByEmailOrLoginAsRoleResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveAllForPriorityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForPriority operation
           */
            public void receiveErrorretrieveAllForPriority(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.UserStoryServiceStub.RetrieveCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCount operation
           */
            public void receiveErrorretrieveCount(java.lang.Exception e) {
            }
                


    }
    