package fr.inria.kairos.sock.generator.example;

import fr.inria.kairos.sock.generator.model.IotSystem;
import fr.inria.kairos.sock.generator.Generator;
import fr.inria.kairos.sock.generator.GeneratorHelper;
import fr.inria.kairos.sock.generator.Interval;
import fr.inria.kairos.sock.generator.io.SockWriter;
import fr.inria.kairos.sock.generator.launch.LaunchGenerator;

import java.io.File;
import java.util.Random;

public class LargeMain {

	private static final int NB_CONFIGURATION = 1;

	private static final int minNbActor = 1;

	private static final int maxNbActor = 3;

	private static final int minPeriodTime = 10;

	private static final int maxPeriodTime = 200;

	private static final int stepPeriodTime = 10;

	private static final int minProcessTime = 1;

	private static final int maxProcessTime = 10;

	private static final int minNbResource = 3;

	private static final int maxNbResource = 4;

	private static final Interval interval = new Interval(0.60d, 0.80d);

	private static final Random random = new Random(23L);

	private static final Generator generator = new Generator(random, minPeriodTime, maxPeriodTime, stepPeriodTime,
			minProcessTime, maxProcessTime, minNbActor, maxNbActor);

	public static void main(String[] args) {
		new File(GeneratorHelper.PATH_OUTPUT + "large/").delete();
		new File(GeneratorHelper.PATH_OUTPUT + "large/").mkdirs();
		new File(GeneratorHelper.PATH_OUTPUT + "large/launch/").mkdirs();
		final SockWriter writer = new SockWriter(GeneratorHelper.PATH_OUTPUT + "large/");
		for (int i = 0 ; i < NB_CONFIGURATION ; i ++) {
			final IotSystem system = new IotSystem("larges" + i);
			final int nbResource = minNbResource  + random.nextInt(maxNbResource - minNbResource);
			for (int j = 0 ; j < nbResource ; j++) {
				System.out.println(j);
				generator.initSystemWithGivenBoundForResource(interval, system, true);
			}
			writer.write(system.getName(), system);
			system.getOwnedActor().stream()
					.forEach(actor -> System.out.println(actor.getName() + " " + actor.getScore(true)));
//			System.out.println(system.getOwnedResource().get(0).getBound());
			System.out.println(
					system.getOwnedActor().stream().map(actor -> actor.getScore(true)).reduce((acc, actor) -> acc + actor));
			writer.write(system.getName() + SockWriter.LAUNCH_EXTENSION, new LaunchGenerator().generateLaunchConfiguration(
					"/test-project/large/" + system.getName() + SockWriter.TSOCK_EXTENSION, false));
		}
	}

}
