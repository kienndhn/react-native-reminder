
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNReminderSpec.h"

@interface Reminder : NSObject <NativeReminderSpec>
#else
#import <React/RCTBridgeModule.h>

@interface Reminder : NSObject <RCTBridgeModule>
#endif

@end
