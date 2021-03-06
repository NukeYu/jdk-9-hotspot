/*
 * Copyright (c) 2005, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package jdk.test.lib.jittester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class TypeUtil {

    public static Collection<Type> getImplicitlyCastable(Collection<Type> types, Type type) {
        ArrayList<Type> result = new ArrayList<>(types);
        Iterator<Type> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().canImplicitlyCastTo(type)) {
                iterator.remove();
            }
        }
        return result;
    }

    public static Collection<Type> getExplicitlyCastable(Collection<Type> types, Type type) {
        ArrayList<Type> result = new ArrayList<>(types);
        Iterator<Type> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().canExplicitlyCastTo(type)) {
                iterator.remove();
            }
        }
        return result;
    }

    public static List<Type> getMoreCapatiousThan(Collection<Type> types, BuiltInType type) {
        ArrayList<Type> result = new ArrayList<>();
        Iterator<Type> iterator = types.iterator();
        while (iterator.hasNext()) {
            try {
                BuiltInType builtInType = (BuiltInType) iterator.next();
                if (builtInType.isMoreCapaciousThan(type)) {
                    result.add(builtInType);
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    public static List<Type> getLessCapatiousOrEqualThan(Collection<Type> types, BuiltInType type) {
        ArrayList<Type> result = new ArrayList<>();
        Iterator<Type> iterator = types.iterator();
        while (iterator.hasNext()) {
            try {
                BuiltInType builtInType = (BuiltInType) iterator.next();
                if (!builtInType.isMoreCapaciousThan(type) || builtInType.equals(type)) {
                    result.add(builtInType);
                }
            } catch (Exception e) {
            }
        }
        return result;
    }
}
