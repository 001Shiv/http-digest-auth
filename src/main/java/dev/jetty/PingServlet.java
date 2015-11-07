/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.jetty;

import java.io.IOException ;

import javax.servlet.ServletOutputStream ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import org.apache.jena.atlas.io.IO ;
import org.apache.jena.atlas.lib.DateTimeUtils ;

/** The ping servlet provides a low costy, uncached endpoint that can be used
 * to determine if this component is running and responding.  For example,
 * a nagios check should use this endpoint.    
 */
public class PingServlet extends HttpServlet
{
    // Ping is special.
    // To avoid excessive logging and id allocation for a "noise" operation,
    // this is a raw servlet.
    public PingServlet() { super() ; } 
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doCommon(req, resp); 
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doCommon(req, resp); 
    }
    

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        doCommon(req, resp); 
    }

    protected void doCommon(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletOps.setNoCache(response) ; 
            response.setContentType("text/plain") ;
            response.setCharacterEncoding("utf-8") ;
            response.setStatus(HttpSC.OK_200);
            ServletOutputStream out = response.getOutputStream() ;
            out.println(DateTimeUtils.nowAsXSDDateTimeString());
        } catch (IOException ex) {
            IO.exception(ex); 
        }
    }
}

