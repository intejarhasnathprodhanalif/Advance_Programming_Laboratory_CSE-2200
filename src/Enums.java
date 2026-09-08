enum DeviceState {
    STANDBY,
    MOTION_DETECTED,
    SOUND_DETECTED,
    ALARM_TRIGGERED
}

public class Enums {
    public static void main(String[] args)
    {
        DeviceState currentState = DeviceState.STANDBY;

                // You cannot assign an integer or a random string here.
                // It MUST be one of the exact DeviceState values.
                currentState = DeviceState.MOTION_DETECTED;

                // Enums work beautifully with switch statements
                switch (currentState) {
                    case STANDBY -> System.out.println("System idle.");
                    case MOTION_DETECTED -> System.out.println("PIR sensor tripped!");
                    case SOUND_DETECTED -> System.out.println("Sound sensor tripped!");
                    case ALARM_TRIGGERED -> System.out.println("Siren active.");
                }

    }
}