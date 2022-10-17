/*
 * Copyright 2006-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package egovframework.example.bat.domain.trade;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @see
 * 
 *      <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *      </pre>
 */

public class ResultVO {
	int siteSeq;
	String result;
	int resptime;
	String resulttext;
	int cdate;
	int ctime;

	public int getSiteSeq() {
		return siteSeq;
	}

	public void setSiteSeq(int siteSeq) {
		this.siteSeq = siteSeq;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getResptime() {
		return resptime;
	}

	public void setResptime(int resptime) {
		this.resptime = resptime;
	}

	public String getResulttext() {
		return resulttext;
	}

	public void setResulttext(String resulttext) {
		this.resulttext = resulttext;
	}

	public int getCdate() {
		return cdate;
	}

	public void setCdate(int cdate) {
		this.cdate = cdate;
	}

	public int getCtime() {
		return ctime;
	}

	public void setCtime(int ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Result [siteSeq=" + siteSeq + ", result=" + result + ", resptime=" + resptime + ", resulttext="
				+ resulttext + ", cdate=" + cdate + ", ctime=" + ctime + "]";
	}

}
