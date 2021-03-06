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

package org.seaborne.auth;

import java.util.Objects ;

import org.apache.commons.codec.digest.DigestUtils ;

/** Operations from RFC 2617, using MD5 (the default) */
class RFC2617 {

    /*package*/ static String KD(String data) {
        return H(data) ;
    }

    /*package*/ static String KD(String secret, String data) {
        return H(secret+":"+data) ;
    }

    /*package*/ static String H(String string) {
        return DigestUtils.md5Hex(string) ;
    }

    /*package*/ static String A1_MD5(String username, String realm, String password) {
        Objects.requireNonNull(username) ;
        Objects.requireNonNull(realm) ;
        Objects.requireNonNull(password) ;
        String s = username+":"+realm+":"+password ;
        return s ;
    }

    /*package*/ static String A1_MD5_sess(String username, String realm, String password, String nonce, String cnonce) {
        Objects.requireNonNull(username) ;
        Objects.requireNonNull(realm) ;
        Objects.requireNonNull(password) ;
        Objects.requireNonNull(nonce) ;
        Objects.requireNonNull(cnonce) ;
        String s = username+":"+realm+":"+password ;
        String x = H(s)+":"+nonce+":"+cnonce ;
        return s ;
    }

    /*package*/static String A2_auth(String method, String uri) {
        return method+":"+uri ;
    }

    /*package*/ static String A2_auth_int(String method, String uri, String entityBody) {
        throw new UnsupportedOperationException() ;
    }
}
