


/$target, where the ApplyOffset() or IsOffsetApplicable() function (tries) to apply the delta-curve$/
std enum ToSubject 24100
{
    LevALL = 0,
    LevLow = 1,
    LevMed = 2,
    LevHigh = 3,
    SwTinnitus = 4
};

/$range, where the ApplyOffset() or IsOffsetApplicable() function (tries) to apply the delta-curve
Complete            Complete
Partial             Partial
Amap                as much as possible$/
std enum Apply 24101
{
    Complete = 0, // Complete
    Partial = 1, // Partial
    Amap = 2 // as much as possible
};

/$parameter indicating a group of channel-arranged hi controls$/
std enum Xf 24200
{
    XfGain = 0,
    XfCompression = 1,
    XfChanMpo = 2
};

/$level curve identifier, ExistsCurveFromLc()
TG                  target gain
IG                  insertion gain$/
std enum LcId 24201
{
    TG = 0, // target gain
    IG = 1 // insertion gain
};

/$level identifier, ExistsCurveFromLc()
Low                 low level
Med                 medium level
High                high level$/
std enum Level 24202
{
    Low = 0, // low level
    Med = 1, // medium level
    High = 2 // high level
};
