/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;


import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

/**
 * Experimental class to offer rudimentary read-only processing of 
 *  of the contentblock of an SDT/ContentControl.
 *  
 *
 *
 * WARNING - APIs expected to change rapidly
 * 
 */
public class XWPFSDTContent  {

    // private final IBody part;
    // private final XWPFDocument document;
    private List<XWPFParagraph> paragraphs = new ArrayList<XWPFParagraph>();
    private List<XWPFTable> tables = new ArrayList<XWPFTable>();
    private List<XWPFRun> runs = new ArrayList<XWPFRun>();
    private List<XWPFSDT> contentControls = new ArrayList<XWPFSDT>();
    private List<ISDTContents> bodyElements = new ArrayList<ISDTContents>();

    public XWPFSDTContent(CTSdtContentRun sdtRun, IBody part, IRunBody parent){
        for (CTR ctr : sdtRun.getRList()){
            XWPFRun run = new XWPFRun((CTR) ctr, parent);
            runs.add(run);
            bodyElements.add(run);
        }
    }
    public XWPFSDTContent(CTSdtContentBlock block, IBody part, IRunBody parent){
        XmlCursor cursor = block.newCursor();
        cursor.selectPath("./*");
        while (cursor.toNextSelection()) {
            XmlObject o = cursor.getObject();
            if (o instanceof CTP) {
                XWPFParagraph p = new XWPFParagraph((CTP) o, part);
                bodyElements.add(p);
                paragraphs.add(p);
            } else if (o instanceof CTTbl) {
                XWPFTable t = new XWPFTable((CTTbl) o, part);
                bodyElements.add(t);
                tables.add(t);
            } else if (o instanceof CTSdtBlock){
                XWPFSDT c = new XWPFSDT(((CTSdtBlock)o), part);
                bodyElements.add(c);
                contentControls.add(c);
            } else if (o instanceof CTR) {
                XWPFRun run = new XWPFRun((CTR) o, parent);
                runs.add(run);
                bodyElements.add(run);
            }
        }
    }

    public String getText(){
        StringBuilder text = new StringBuilder();
        boolean addNewLine = false;
        for (int i = 0; i < bodyElements.size(); i++){
            Object o = bodyElements.get(i);
            if (o instanceof XWPFParagraph){
                text.append(((XWPFParagraph)o).getText());
                addNewLine = true;
            } else if (o instanceof XWPFTable){
                text.append(((XWPFTable)o).getText());
                addNewLine = true;
            } else if (o instanceof XWPFSDT){
                text.append(((XWPFSDT)o).getContent().getText());
                addNewLine = true;
            } else if (o instanceof XWPFRun){
                text.append(((XWPFRun)o).toString());
                addNewLine = false;
            }
            if (addNewLine == true && i < bodyElements.size()-1){
                text.append("\n");
            }
        }
        return text.toString();
    }

    public String toString(){
        return getText();
    }
}
