
/**
 * EntityStateServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  EntityStateServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class EntityStateServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public EntityStateServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public EntityStateServiceCallbackHandler(){
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
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.GetByIDResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.UpdatePartialResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAll operation
           */
            public void receiveErrorretrieveAll(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update method
            * override this method for handling normal response from update operation
            */
           public void receiveResultupdate(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.UpdateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update operation
           */
            public void receiveErrorupdate(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveTpEventsForEntityState method
            * override this method for handling normal response from retrieveTpEventsForEntityState operation
            */
           public void receiveResultretrieveTpEventsForEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveTpEventsForEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveTpEventsForEntityState operation
           */
            public void receiveErrorretrieveTpEventsForEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveCount method
            * override this method for handling normal response from retrieveCount operation
            */
           public void receiveResultretrieveCount(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveCountResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.DeleteResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delete operation
           */
            public void receiveErrordelete(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for create method
            * override this method for handling normal response from create operation
            */
           public void receiveResultcreate(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.CreateResponse result
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
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieve operation
           */
            public void receiveErrorretrieve(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeTpEventFromEntityState method
            * override this method for handling normal response from removeTpEventFromEntityState operation
            */
           public void receiveResultremoveTpEventFromEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RemoveTpEventFromEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeTpEventFromEntityState operation
           */
            public void receiveErrorremoveTpEventFromEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForProcess method
            * override this method for handling normal response from retrieveAllForProcess operation
            */
           public void receiveResultretrieveAllForProcess(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAllForProcessResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForProcess operation
           */
            public void receiveErrorretrieveAllForProcess(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrieveAllForRole method
            * override this method for handling normal response from retrieveAllForRole operation
            */
           public void receiveResultretrieveAllForRole(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrieveAllForRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrieveAllForRole operation
           */
            public void receiveErrorretrieveAllForRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeState method
            * override this method for handling normal response from changeState operation
            */
           public void receiveResultchangeState(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.ChangeStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeState operation
           */
            public void receiveErrorchangeState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for retrievePage method
            * override this method for handling normal response from retrievePage operation
            */
           public void receiveResultretrievePage(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.RetrievePageResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from retrievePage operation
           */
            public void receiveErrorretrievePage(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTpEventToEntityState method
            * override this method for handling normal response from addTpEventToEntityState operation
            */
           public void receiveResultaddTpEventToEntityState(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.AddTpEventToEntityStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTpEventToEntityState operation
           */
            public void receiveErroraddTpEventToEntityState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getIDs method
            * override this method for handling normal response from getIDs operation
            */
           public void receiveResultgetIDs(
                    org.eclipse.mylyn.targetprocess.modules.services.EntityStateServiceStub.GetIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getIDs operation
           */
            public void receiveErrorgetIDs(java.lang.Exception e) {
            }
                


    }
    