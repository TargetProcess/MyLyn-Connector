
/**
 * GeneralServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  GeneralServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GeneralServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GeneralServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GeneralServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.UpdatePartialResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForOwner method
            * override this method for handling normal response from retrieveAllForOwner operation
            */
           public void receiveResultretrieveAllForOwner(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveAllForOwnerResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForOwner operation
           */
            public void receiveErrorretrieveAllForOwner(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeAttachmentFromGeneral method
            * override this method for handling normal response from removeAttachmentFromGeneral operation
            */
           public void receiveResultremoveAttachmentFromGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RemoveAttachmentFromGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeAttachmentFromGeneral operation
           */
            public void receiveErrorremoveAttachmentFromGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeCommentFromGeneral method
            * override this method for handling normal response from removeCommentFromGeneral operation
            */
           public void receiveResultremoveCommentFromGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RemoveCommentFromGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeCommentFromGeneral operation
           */
            public void receiveErrorremoveCommentFromGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveCountResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.DeleteResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveAllForLastCommentUserResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.CreateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProject method
            * override this method for handling normal response from retrieveAllForProject operation
            */
           public void receiveResultretrieveAllForProject(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveAllForProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProject operation
           */
            public void receiveErrorretrieveAllForProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttachmentToGeneral method
            * override this method for handling normal response from addAttachmentToGeneral operation
            */
           public void receiveResultaddAttachmentToGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.AddAttachmentToGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttachmentToGeneral operation
           */
            public void receiveErroraddAttachmentToGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAttachmentsForGeneral method
            * override this method for handling normal response from retrieveAttachmentsForGeneral operation
            */
           public void receiveResultretrieveAttachmentsForGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveAttachmentsForGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAttachmentsForGeneral operation
           */
            public void receiveErrorretrieveAttachmentsForGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCommentsForGeneral method
            * override this method for handling normal response from retrieveCommentsForGeneral operation
            */
           public void receiveResultretrieveCommentsForGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.RetrieveCommentsForGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveCommentsForGeneral operation
           */
            public void receiveErrorretrieveCommentsForGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addCommentToGeneral method
            * override this method for handling normal response from addCommentToGeneral operation
            */
           public void receiveResultaddCommentToGeneral(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.AddCommentToGeneralResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addCommentToGeneral operation
           */
            public void receiveErroraddCommentToGeneral(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.GeneralServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                


    }
    