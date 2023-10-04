import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { multiply, scheduleAlarm } from 'react-native-reminder';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    multiply(3, 7).then(setResult);
    scheduleAlarm({ scheduleTime: '04-10-2023 10:02:00', id: 27 }).then(
      (result) => console.log(result)
    );
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
