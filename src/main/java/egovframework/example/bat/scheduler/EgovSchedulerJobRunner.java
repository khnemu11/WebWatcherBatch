package egovframework.example.bat.scheduler;

import java.util.ArrayList;
import java.util.List;

import egovframework.rte.bat.core.launch.support.EgovSchedulerRunner;

/**
 * @author 배치실행개발팀
 * @since 2012. 07.25
 * @version 1.0
 * @see
 *  <pre>
 *      개정이력(Modification Information)
 *
 *   수정일      수정자           수정내용
 *  ------- -------- ---------------------------
 *  2012. 07.25  배치실행개발팀     최초 생성
 *  </pre>
 */

public class EgovSchedulerJobRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<String> jobPaths = new ArrayList<String>();

		/*
		 * 1. DB 실행 예제(DB To File)에서 사용 할 Batch Job이 기술 된 xml파일 경로들(jobPaths)
		 */
		jobPaths.add("/egovframework/batch/job/ibatisToDelimitedJob.xml");
		jobPaths.add("/egovframework/batch/job/ibatisToFixedLengthJob.xml");

		/*
		 * 2. DB 실행 예제(DB To DB)에서 사용 할 Batch Job이 기술 된 xml파일 경로들(jobPaths)
		 */
		jobPaths.add("/egovframework/batch/job/ibatisToIbatisJob.xml");
		jobPaths.add("/egovframework/batch/job/jdbcToJdbcJob.xml");

		/*
		 * EgovSchedulerRunner에 contextPath, schedulerJobPath, jobPaths를 인수로 넘겨서 실행한다.
		 * contextPath: Batch Job 실행에 필요한 context 정보가 기술된 xml파일 경로
		 * schedulerJobPath: Scheduler의 Trigger가 수행할 SchedulerJob(ex: QuartzJob)이 기술된 xml파일 경로
		 * jobPaths: Batch Job이 기술 된 xml 파일 경로들
		 * delayTime: Scheduler 실행을 위해 ApplicationContext를 종료를 지연시키는 시간(실행시간)
		 *            (기본 30000 milliseconds: 30초)
		 */
			EgovSchedulerRunner egovSchedulerRunner = new EgovSchedulerRunner("/egovframework/batch/context-batch-scheduler.xml", "/egovframework/batch/context-scheduler-job.xml",
					jobPaths, 300000);
			egovSchedulerRunner.start();
	}
}
