package com.example.unsplashattestation.data.usecase

import com.example.unsplashattestation.domain.model.Photo
import com.example.unsplashattestation.domain.repository.PhotosPagingSourceRepository
import com.example.unsplashattestation.domain.usecase.PhotoLikeUseCase
import javax.inject.Inject

class PhotoLikeUseCaseImpl @Inject constructor(
    private val repository: PhotosPagingSourceRepository
    ): PhotoLikeUseCase {

    /** если прям доебываться это уже не юзкейс
     * юзкейс должен выполнять одно единственное действия...в нашем у тебя тут еще
     * и запись в базу данных
     * если пошел по юзкейсом выноси запись данных в отдельный юзкейз
     * Если просто цель показать что ты могешь ими пользоваться то можно создать парочку юзкейсов
     * а пользоваться репозиторием, это не запрещено
     * и да я тоже чутка покапался в вопросе юзкей не должен возвращать ни энтити ни дто*/

    override suspend fun likePhoto(item: Photo) {
        val response = if (item.likedByUser) repository.deleteLike(item.id).photo
        else repository.setLike(item.id).photo
        val newItem =
            item.copy(likedByUser = response.likedByUser, likes = response.likes)
        repository.updateLikeDB(newItem.toPhotoEntity())
    }
}