
/**
 * FileServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.6  Built on : Aug 30, 2011 (10:00:16 CEST)
 */

    package org.eclipse.mylyn.targetprocess.modules.services;

    /**
     *  FileServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class FileServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public FileServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public FileServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getFilesList method
            * override this method for handling normal response from getFilesList operation
            */
           public void receiveResultgetFilesList(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFilesListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFilesList operation
           */
            public void receiveErrorgetFilesList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ping method
            * override this method for handling normal response from ping operation
            */
           public void receiveResultping(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.PingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ping operation
           */
            public void receiveErrorping(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for downloadChunk method
            * override this method for handling normal response from downloadChunk operation
            */
           public void receiveResultdownloadChunk(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.DownloadChunkResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from downloadChunk operation
           */
            public void receiveErrordownloadChunk(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkFileHash method
            * override this method for handling normal response from checkFileHash operation
            */
           public void receiveResultcheckFileHash(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.CheckFileHashResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkFileHash operation
           */
            public void receiveErrorcheckFileHash(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getFileSize method
            * override this method for handling normal response from getFileSize operation
            */
           public void receiveResultgetFileSize(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.GetFileSizeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFileSize operation
           */
            public void receiveErrorgetFileSize(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for appendChunk method
            * override this method for handling normal response from appendChunk operation
            */
           public void receiveResultappendChunk(
                    org.eclipse.mylyn.targetprocess.modules.services.FileServiceStub.AppendChunkResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from appendChunk operation
           */
            public void receiveErrorappendChunk(java.lang.Exception e) {
            }
                


    }
    