import {
  requireNativeComponent,
  View,
} from 'react-native'

module.exports = requireNativeComponent('RCTSlidingTabViewPagerAndroid', {
  name: 'SlidingTabViewPagerAndroid',
  propTypes: {
    ...View.propTypes
  },
})
