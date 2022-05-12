package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bowser;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.ground.*;
import game.items.Consumables;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());
			ArrayList<GameMap> worldList = new ArrayList<>();

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
					new Sprout(), new WarpPipe(), new Lava());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				"............................................C....##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);
			worldList.add(gameMap);

			List<String> lavaMap = Arrays.asList(
				"C..........................................###........+..........",
				"............+............+..................###..................",
				".............................................###.................",
				"..............................................###................",
				"...............................................###...............",
				"................................................###..............",
				".................+........................LLL...###..............",
				"..........................................LLL...###..............",
				"..........................................LLL..###...............",
				".........+..............................+#____####...............",
				".......................................+#_____###................",
				".......................................+#______###...............",
				"........................................+#_____###...............",
				"........................+.......................###..............",
				".................................................###.............",
				"..................................................###............",
				"...................+...............................###...........");


			GameMap lavaGameMap = new GameMap(groundFactory, lavaMap);
			world.addGameMap(lavaGameMap);
			worldList.add(lavaGameMap);

			Actor mario = new Player("Mario", 'm', 100, worldList);
			world.addPlayer(mario, gameMap.at(42, 10));

			Actor toad = new Toad();
			gameMap.addActor(toad, gameMap.at(46, 10));

			Actor bowser = new Bowser();
			lavaGameMap.addActor(bowser, lavaGameMap.at(63, 16));

			Actor peach = new PrincessPeach();
			lavaGameMap.addActor(peach, lavaGameMap.at(64,16));

			Consumables SuperMushroom = new SuperMushroom();
			gameMap.at(42,11).addItem(SuperMushroom);

			Consumables PowerStar = new PowerStar();
			gameMap.at(42,9).addItem(PowerStar);

			Wrench wrench = new Wrench();
			gameMap.at(43, 9).addItem(wrench);

			world.run();

	}
}
