
/**
 * TaskServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  TaskServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class TaskServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public TaskServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public TaskServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveOpenForUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveOpenForUser operation
           */
            public void receiveErrorretrieveOpenForUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToTask method
            * override this method for handling normal response from addAttachmentToTask operation
            */
           public void receiveResultaddAttachmentToTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddAttachmentToTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToTask operation
           */
            public void receiveErroraddAttachmentToTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForTask method
            * override this method for handling normal response from retrieveAttachmentsForTask operation
            */
           public void receiveResultretrieveAttachmentsForTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAttachmentsForTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForTask operation
           */
            public void receiveErrorretrieveAttachmentsForTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTeamFromTask method
            * override this method for handling normal response from removeTeamFromTask operation
            */
           public void receiveResultremoveTeamFromTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RemoveTeamFromTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTeamFromTask operation
           */
            public void receiveErrorremoveTeamFromTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeEffort method
            * override this method for handling normal response from changeEffort operation
            */
           public void receiveResultchangeEffort(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.ChangeEffortResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.DeleteResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveOpenForMeResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.ChangeStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeState operation
           */
            public void receiveErrorchangeState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForTask method
            * override this method for handling normal response from retrieveCommentsForTask operation
            */
           public void receiveResultretrieveCommentsForTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCommentsForTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForTask operation
           */
            public void receiveErrorretrieveCommentsForTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.UpdateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AssignUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignUser operation
           */
            public void receiveErrorassignUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForEntityState method
            * override this method for handling normal response from retrieveAllForEntityState operation
            */
           public void receiveResultretrieveAllForEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForEntityStateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.CreateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AssignUserByEmailOrLoginResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForReleaseResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForIterationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForIteration operation
           */
            public void receiveErrorretrieveAllForIteration(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToTask method
            * override this method for handling normal response from addCommentToTask operation
            */
           public void receiveResultaddCommentToTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddCommentToTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToTask operation
           */
            public void receiveErroraddCommentToTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTeamsForTask method
            * override this method for handling normal response from retrieveTeamsForTask operation
            */
           public void receiveResultretrieveTeamsForTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveTeamsForTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTeamsForTask operation
           */
            public void receiveErrorretrieveTeamsForTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRevisionAssignablesForTask method
            * override this method for handling normal response from retrieveRevisionAssignablesForTask operation
            */
           public void receiveResultretrieveRevisionAssignablesForTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveRevisionAssignablesForTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRevisionAssignablesForTask operation
           */
            public void receiveErrorretrieveRevisionAssignablesForTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRevisionAssignableToTask method
            * override this method for handling normal response from addRevisionAssignableToTask operation
            */
           public void receiveResultaddRevisionAssignableToTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddRevisionAssignableToTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRevisionAssignableToTask operation
           */
            public void receiveErroraddRevisionAssignableToTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeRevisionAssignableFromTask method
            * override this method for handling normal response from removeRevisionAssignableFromTask operation
            */
           public void receiveResultremoveRevisionAssignableFromTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RemoveRevisionAssignableFromTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeRevisionAssignableFromTask operation
           */
            public void receiveErrorremoveRevisionAssignableFromTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updatePartial method
            * override this method for handling normal response from updatePartial operation
            */
           public void receiveResultupdatePartial(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.UpdatePartialResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetPrioritiesResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromTask method
            * override this method for handling normal response from removeAttachmentFromTask operation
            */
           public void receiveResultremoveAttachmentFromTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RemoveAttachmentFromTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromTask operation
           */
            public void receiveErrorremoveAttachmentFromTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveRoleEffortsForTask method
            * override this method for handling normal response from retrieveRoleEffortsForTask operation
            */
           public void receiveResultretrieveRoleEffortsForTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveRoleEffortsForTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveRoleEffortsForTask operation
           */
            public void receiveErrorretrieveRoleEffortsForTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForUserStory method
            * override this method for handling normal response from retrieveAllForUserStory operation
            */
           public void receiveResultretrieveAllForUserStory(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForUserStoryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForUserStory operation
           */
            public void receiveErrorretrieveAllForUserStory(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTeamToTask method
            * override this method for handling normal response from addTeamToTask operation
            */
           public void receiveResultaddTeamToTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AddTeamToTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTeamToTask operation
           */
            public void receiveErroraddTeamToTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromTask method
            * override this method for handling normal response from removeCommentFromTask operation
            */
           public void receiveResultremoveCommentFromTask(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RemoveCommentFromTaskResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromTask operation
           */
            public void receiveErrorremoveCommentFromTask(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForLastCommentUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForLastCommentUser operation
           */
            public void receiveErrorretrieveAllForLastCommentUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getByID method
            * override this method for handling normal response from getByID operation
            */
           public void receiveResultgetByID(
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AssignUserAsRoleResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.AssignUserByEmailOrLoginAsRoleResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveAllForPriorityResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.TaskServiceStub.RetrieveCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCount operation
           */
            public void receiveErrorretrieveCount(java.lang.Exception e) {
            }
                


    }
    