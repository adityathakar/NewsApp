package com.example.data.mapper

import com.example.data.model.SourceApiModel
import com.example.domain.model.Source
import javax.inject.Inject

class InboundSourceMapper @Inject constructor() : BaseMapper<SourceApiModel, Source>() {

    override fun transform(input: SourceApiModel): Source {
        return Source(
            id = input.id,
            name = input.name,
            isSelected = false
        )
    }
}