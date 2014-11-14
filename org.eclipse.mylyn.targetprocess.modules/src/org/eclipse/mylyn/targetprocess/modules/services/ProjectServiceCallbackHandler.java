
/**
 * ProjectServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  ProjectServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ProjectServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ProjectServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ProjectServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.UpdatePartialResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updatePartial operation
           */
            public void receiveErrorupdatePartial(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForCompany method
            * override this method for handling normal response from retrieveAllForCompany operation
            */
           public void receiveResultretrieveAllForCompany(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForCompanyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForCompany operation
           */
            public void receiveErrorretrieveAllForCompany(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAll method
            * override this method for handling normal response from retrieveAll operation
            */
           public void receiveResultretrieveAll(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForProject method
            * override this method for handling normal response from retrieveAttachmentsForProject operation
            */
           public void receiveResultretrieveAttachmentsForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAttachmentsForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForProject operation
           */
            public void receiveErrorretrieveAttachmentsForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromProject method
            * override this method for handling normal response from removeAttachmentFromProject operation
            */
           public void receiveResultremoveAttachmentFromProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RemoveAttachmentFromProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromProject operation
           */
            public void receiveErrorremoveAttachmentFromProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProgramOfProject method
            * override this method for handling normal response from retrieveAllForProgramOfProject operation
            */
           public void receiveResultretrieveAllForProgramOfProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForProgramOfProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProgramOfProject operation
           */
            public void receiveErrorretrieveAllForProgramOfProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToProject method
            * override this method for handling normal response from addAttachmentToProject operation
            */
           public void receiveResultaddAttachmentToProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.AddAttachmentToProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToProject operation
           */
            public void receiveErroraddAttachmentToProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveProjectMembersForProject method
            * override this method for handling normal response from retrieveProjectMembersForProject operation
            */
           public void receiveResultretrieveProjectMembersForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveProjectMembersForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveProjectMembersForProject operation
           */
            public void receiveErrorretrieveProjectMembersForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForProject method
            * override this method for handling normal response from retrieveCommentsForProject operation
            */
           public void receiveResultretrieveCommentsForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveCommentsForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForProject operation
           */
            public void receiveErrorretrieveCommentsForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromProject method
            * override this method for handling normal response from removeCommentFromProject operation
            */
           public void receiveResultremoveCommentFromProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RemoveCommentFromProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromProject operation
           */
            public void receiveErrorremoveCommentFromProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveCountResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCount operation
           */
            public void receiveErrorretrieveCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delete method
            * override this method for handling normal response from delete operation
            */
           public void receiveResultdelete(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForLastCommentUser method
            * override this method for handling normal response from retrieveAllForLastCommentUser operation
            */
           public void receiveResultretrieveAllForLastCommentUser(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForLastCommentUserResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.CreateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from create operation
           */
            public void receiveErrorcreate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieve method
            * override this method for handling normal response from retrieve operation
            */
           public void receiveResultretrieve(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProcess method
            * override this method for handling normal response from retrieveAllForProcess operation
            */
           public void receiveResultretrieveAllForProcess(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForProcessResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProcess operation
           */
            public void receiveErrorretrieveAllForProcess(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addReleaseToProject method
            * override this method for handling normal response from addReleaseToProject operation
            */
           public void receiveResultaddReleaseToProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.AddReleaseToProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addReleaseToProject operation
           */
            public void receiveErroraddReleaseToProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToProject method
            * override this method for handling normal response from addCommentToProject operation
            */
           public void receiveResultaddCommentToProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.AddCommentToProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToProject operation
           */
            public void receiveErroraddCommentToProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeProjectMemberFromProject method
            * override this method for handling normal response from removeProjectMemberFromProject operation
            */
           public void receiveResultremoveProjectMemberFromProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RemoveProjectMemberFromProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeProjectMemberFromProject operation
           */
            public void receiveErrorremoveProjectMemberFromProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveReleasesForProject method
            * override this method for handling normal response from retrieveReleasesForProject operation
            */
           public void receiveResultretrieveReleasesForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RetrieveReleasesForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveReleasesForProject operation
           */
            public void receiveErrorretrieveReleasesForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeReleaseFromProject method
            * override this method for handling normal response from removeReleaseFromProject operation
            */
           public void receiveResultremoveReleaseFromProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.RemoveReleaseFromProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeReleaseFromProject operation
           */
            public void receiveErrorremoveReleaseFromProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addProjectMemberToProject method
            * override this method for handling normal response from addProjectMemberToProject operation
            */
           public void receiveResultaddProjectMemberToProject(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.AddProjectMemberToProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addProjectMemberToProject operation
           */
            public void receiveErroraddProjectMemberToProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.ProjectServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                


    }
    