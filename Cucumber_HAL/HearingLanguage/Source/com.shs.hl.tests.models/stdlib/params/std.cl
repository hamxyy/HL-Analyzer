/$target, where the ApplyOffset() or IsOffsetApplicable() function (tries) to apply the delta-curve$/
std enum ToSubject 24100 {
    LevALL            = 0,
    LevLow            = 1,
    LevMed            = 2,
    LevHigh           = 3,
    SwTinnitus        = 4
};

/$range, where the ApplyOffset() or IsOffsetApplicable() function (tries) to apply the delta-curve
Complete        Complete
Partial         Partial
Amap            as much as possible$/
std enum Apply 24101 {
    Complete          = 0,    //Complete
    Partial           = 1,    //Partial
    Amap              = 2    //as much as possible
};

/$parameter indicating a group of channel-arranged hi controls$/
std enum Xf 24200 {
    XfGain            = 0,
    XfCompression     = 1,
    XfChanMpo         = 2
};

