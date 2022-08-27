package p1_snl;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import java.io.IOException;
import java.util.Properties;
import p1_snl.annotations.MultipleSixDecisionDiceRoll;
import p1_snl.annotations.TraditionalDiceRoll;
import p1_snl.models.DiceConfiguration;
import p1_snl.models.GameBoard;
import p1_snl.models.IGameBoardFactory;
import p1_snl.service.GameInputReceiverImpl;
import p1_snl.service.IDiceRollStrategy;
import p1_snl.service.IGameInputReceiver;
import p1_snl.service.IGamePlayServiceFactory;
import p1_snl.service.SpecialSixDiceRoll;
import p1_snl.service.TraditionalGamePlayService;

public class SNLModule extends AbstractModule {

  @Override
  protected void configure() {
    configurePropertiesFile();
    bind(IGameInputReceiver.class).to(GameInputReceiverImpl.class);
    install(new FactoryModuleBuilder()
        .build(Key.get(new TypeLiteral<IGameBoardFactory<GameBoard>>() {
        })));
    install(new FactoryModuleBuilder()
        .build(Key.get(new TypeLiteral<IGamePlayServiceFactory<TraditionalGamePlayService>>() {
        })));
    bind(IDiceRollStrategy.class).annotatedWith(TraditionalDiceRoll.class).to(
        p1_snl.service.TraditionalDiceRoll.class);
    bind(IDiceRollStrategy.class).annotatedWith(MultipleSixDecisionDiceRoll.class).to(
        SpecialSixDiceRoll.class);

  }

  private void configurePropertiesFile() {
    try {
      Properties props = new Properties();
      props.load(getClass().getClassLoader().getResourceAsStream("gameconfiguration.properties"));
      Names.bindProperties(binder(), props);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Provides
  public DiceConfiguration get(@Named("dice.low.range") final int lowRange,
      @Named("dice.high.range") final int highRange,
      @Named("dice.number.of.dice") final int numberOfDice) {
    return new DiceConfiguration(lowRange, highRange, numberOfDice);
  }

}
