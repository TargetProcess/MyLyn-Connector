
/**
 * MyAssignmentsServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:24 EDT)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  MyAssignmentsServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MyAssignmentsServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MyAssignmentsServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MyAssignmentsServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getMyAssigments method
            * override this method for handling normal response from getMyAssigments operation
            */
           public void receiveResultgetMyAssigments(
                    org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssigmentsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyAssigments operation
           */
            public void receiveErrorgetMyAssigments(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyAssignmentById method
            * override this method for handling normal response from getMyAssignmentById operation
            */
           public void receiveResultgetMyAssignmentById(
                    org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyAssignmentByIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyAssignmentById operation
           */
            public void receiveErrorgetMyAssignmentById(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMyToDoList method
            * override this method for handling normal response from getMyToDoList operation
            */
           public void receiveResultgetMyToDoList(
                    org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.GetMyToDoListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMyToDoList operation
           */
            public void receiveErrorgetMyToDoList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for changeState method
            * override this method for handling normal response from changeState operation
            */
           public void receiveResultchangeState(
                    org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.ChangeStateResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from changeState operation
           */
            public void receiveErrorchangeState(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for submitTime method
            * override this method for handling normal response from submitTime operation
            */
           public void receiveResultsubmitTime(
                    org.eclipse.mylyn.targetprocess.modules.services.MyAssignmentsServiceStub.SubmitTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from submitTime operation
           */
            public void receiveErrorsubmitTime(java.lang.Exception e) {
            }
                


    }
    